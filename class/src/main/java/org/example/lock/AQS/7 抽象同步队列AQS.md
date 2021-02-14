> AbstractQueuedSynchronizer抽象同步队列，简称AQS。它是实现同步器的基础组件，并发包中锁的底层就是使用AQS实现的。另外，大多数开发者可能永远不会直接使用AQS，但是知道原理对于结构设计还是很有帮助的。下面是AQS的类图结构
>
> ——《java并发编程之美》

# 抽象同步队列AQS——锁的底层支持

AQS类图结构：

![image-20200623162252721](https://cdn.jsdelivr.net/gh/yoveuio/images/images/202020200623162844.png)

从图中可以看出，队列元素是Node。AQS使用`head`与`tail`记录队首和队尾元素，Node中的`Thread`存放线程，使用`prev`与`next`记录相邻节点，很明显AQS是一个双向队列。

AQS包含两个队列：同步队列和条件队列



## Node类

接下来我们看看Node节点内部的属性有什么用：

```java
static final class Node {
    /**
     * 当前节点是共享的
     */
    static final Node SHARED = new Node();
    /**
     * 当前节点是排他的
     */
    static final Node EXCLUSIVE = null;

    /**
     * waitStatus值，指示线程已取消
     */
    static final int CANCELLED = 1;
    /**
     * waitStatus值，指示后续线程需要释放
     */
    static final int SIGNAL = -1;
    /**
     * waitStatus值，指示线程条件条件队列
     */
    static final int CONDITION = -2;
    /**
     * waitStatus值，指示下一个acquireShared应该无条件传播
     */
    static final int PROPAGATE = -3;

    volatile int waitStatus;

    /**
     * 前一个节点
     */
    volatile Node prev;

    /**
     * 后一个节点
     */
    volatile Node next;

    /**
     * 拥有当前节点的线程
     */
    volatile Thread thread;

    /**
     * 对于同步队列，代表着共享还是排它节点
     * 对于条件队列，代表下一个节点
     */
    Node nextWaiter;
}
```

## ConditionObject类

AQS有个内部类`ConditionObject`，用来结合锁实现线程同步。因为是内部类，conditionObject可以直接访问AQS内部的变量。**conditionObject实际上是一个条件变量，用来存放被await方法阻塞的线程**。

## AQS内部信息

对于AQS内部来说，其维持了单一的状态信息`state`，可以通过`getState`、`setState`、`compareAndSetState`函数修改其值。对于锁的目的的不同，state的含义也是不一样的。

对于`ReentrantLock`的实现来说，state可以用来表示当前线程获取锁的可重入次数。对于读写锁`ReentrantReadWriteLock`来说，state的高16位表示读状态，也就是获取该读锁的次数，低16位表示获取到写锁的线程的可重入次数；对于`CountDownlatch`来说，state用来表示计数器当前的值。

> 🔺：对于AQS来说，线程同步的关键是对状态值state进行操作。根据state是否属于一个线程，操作方式分为独占和共享方法。
>
> 在独占方式下获取和释放资源使用得方法为`void acquire(int arg),void acquireInterruptibly(int arg),boolean release(int arg)`；
>
> 在共享方式下获取和释放资源得方法为`void acquireShared(int arg),void acquireSharedInterruptibly(int arg),boolean releaseShared(int arg)`。

**使用独占方式获取的资源是与具体线程绑定的**，就是说一个线程获取到了资源就会标记是这个线程获取到了，其他线程再尝试操作state获取资源时会发现当前该资源不是自己持有的，就会在获取失败后被阻塞。比如独占锁`ReentrantLock`的实现，当一个线程获取到了这个锁的时候，在AQS内部会首先使用CAS操作把`state`状态值从0变成1，然后设置当前锁的持有者为当前线程。当该线程再次获取锁时发现他是锁的持有者，就会将state从1变成2，以此类推。另外一个线程获取锁时发现自己并不是该锁的持有者就会被放入AQS阻塞队列后挂起。

**对应共享方式的资源与具体线程是不相关的**，当多个线程去请求资源时通过CAS方式竞争获取资源，当一个线程获取到了资源后，另外一个线程再次去获取时，如果档期那资源还能满足它的需要，则当前线程只需要CAS方式进行获取即可。比如`Semaphore`信号量，当一个线程通过`acquire()`方法获取信号量时，会首先看当前信号量个数是否满足需要，不满足则把当前线程放入阻塞队列，如果满足则通过自旋CAS获取信号量。

# 独占方式和共享方式的源码解读

## 独占方式

### 获取独占资源

```java
public final void acquire(int arg) {
    // 入参代表使用排他模式
    // 如果入参失败就将AQS同步队列的尾部
    if (!tryAcquire(arg) &&
        // 作用有两个：1.阻塞当前节点；2.节点被唤醒时能够获取锁
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        // 上面步骤失败就打断线程
        selfInterrupt();
}
```

当线程调用`acquire`方法获取独占资源时，会首先使用`tryAcquire`方法获取独占资源，具体是设置状态变量state的值，成功则直接返回，失败则将当前线程封装为`Node.EXCLUSIVE`的Node结点插入到AQS阻塞队列的尾部。并调用`LockSupport.park(this)`方法挂起自己。

```java
/**
  * 将node追加到同步队列的队尾
  **/
private Node addWaiter(Node mode) {
    // 初始化
    Node node = new Node(Thread.currentThread(), mode);
    // 先尝试放一下，如果成功立马返回，如果不行再入队
    // 这种逻辑在Java原码中非常常见，先简单的尝试一下，成功立马返回。如果不行再while循环
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    enq(node);
    return node;
}
```

**入队操作**

```java
// 线程加入队列的方法
// 需要注意返回值是node前一个节点
private Node enq(final Node node) {
    for (;;) {
        Node t = tail;
        if (t == null) { // Must initialize
            if (compareAndSetHead(new Node()))
                tail = head;
        } else {
            node.prev = t;
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return t;
            }
        }
    }
}
```

**阻塞当前线程**

```java
// 主要做两件事：
// 1.通过不断地自旋尝试使自己前一个节点的状态变成signal，然后阻塞自己
// 2.获得锁的线程执行完成之后，释放锁时，会把阻塞的node唤醒，node唤醒之后再次自旋，尝试获得锁
// 返回false表示获得锁成功，返回true表示失败
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            // 选择上一个节点
            final Node p = node.predecessor();
            // 如果当前节点的上一个节点是head，就尝试tryAcquire，如果成功立马把自己设置成head，把上一个节点移出
            // 如果失败尝试进入同步队列
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            // 将node的前一个节点状态置为SINGAL
            // 只要前一个节点状态时SIGNAL，自己就可以阻塞了
            // parkAndCheckInterrupt 阻塞当前线程
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

**关闭操作**

```java
// 确认前一个节点是否有效，无效的话，一直往前找到状态不是取消的节点
// 把前一个节点状态置为SINGAL
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
    int ws = pred.waitStatus;
    // 如果前一个节点是SINGAL了直接返回，不需要自旋
    if (ws == Node.SIGNAL)
        /*
         * This node has already set status asking a release
         * to signal it, so it can safely park.
         */
        return true;
    if (ws > 0) {
        /*
         * Predecessor was cancelled. Skip over predecessors and
         * indicate retry.
         */
        do {
            node.prev = pred = pred.prev;
        } while (pred.waitStatus > 0);
        pred.next = node;
    } else {
        /*
         * waitStatus must be 0 or PROPAGATE.  Indicate that we
         * need a signal, but don't park yet.  Caller will need to
         * retry to make sure it cannot acquire before parking.
         */
        compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
    }
    return false;
}
```

acquire方法大致分为三步：

1. 使用tryAcquire方法尝试获取锁，获得锁直接返回，获取不到锁的走2
2. 把当前节点组装成节点Node，追加到同步队列的尾部
3. 自旋，使同步队列中当前节点的前置节点的状态为signal，然后阻塞自己

> AQS是一种锁的框架，`tryAcquire()`和下文介绍的`tryRelease()`方法一般留给具体锁来实现。和`state`状态变量一样，这两个方法根据实际需求定制。

#### 释放资源

```java
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```

当一个线程调用`release(int arg)`方法时会尝试使用`tryRelease()`操作释放资源，这里是设置状态变量state的值，然后调用`LockSupport.unpark(thread)`方法激活AQS队列里面被阻塞的一个线程(Thread)。被激活的线程则使用`tryAcquire`尝试，看档期那状态变量state的值是否能满足自己的需要，满足则线程激活。

### 共享方式

####  获取共享资源

```java
public final void acquireShared(int arg) {
    if (tryAcquireShared(arg) < 0)
        doAcquireShared(arg);
}
```

与排他锁大致相同

接下来简单说一下不同的位置：

1. 尝试获得锁使用tryAcquireShared方法
2. 节点如果获得共享锁，还会去唤醒自己的后续节点，一起来获得该锁(`setHeadAndPropagate`方法)

```java
// 1.把当前节点设置成头节点
// 2.看看后续节点有无正在等待，并且也是共享模式，有的话唤醒这些节点
private void setHeadAndPropagate(Node node, int propagate) {
    Node h = head; // Record old head for check below
    // 设置成头节点
    setHead(node);
    /*
     * Try to signal next queued node if:
     *   Propagation was indicated by caller,
     *     or was recorded (as h.waitStatus either before
     *     or after setHead) by a previous operation
     *     (note: this uses sign-check of waitStatus because
     *      PROPAGATE status may transition to SIGNAL.)
     * and
     *   The next node is waiting in shared mode,
     *     or we don't know, because it appears null
     *
     * The conservatism in both of these checks may cause
     * unnecessary wake-ups, but only when there are multiple
     * racing acquires/releases, so most need signals now or soon
     * anyway.
     */
    if (propagate > 0 || h == null || h.waitStatus < 0 ||
        (h = head) == null || h.waitStatus < 0) {
        Node s = node.next;
        if (s == null || s.isShared())
            // 释放后置节点
            doReleaseShared();
    }
}
```

#### 释放资源

```java
public final boolean releaseShared(int arg) {
    if (tryReleaseShared(arg)) {
        doReleaseShared();
        return true;
    }
    return false;
}
```

### 对中断进行响应

与`acquire`和`acquireShared`有两个相对应的方法`acquireInterruptibly`和`acquireSharedInterruptibly`方法

不带Interruptibly关键字的方法的意思是不对中断进行相应，也就是线程在调用不带Interruptibly关键字的方法获取资源时，或者获取资源失败被挂起时，其他线程中断了该线程，那么该线程不会因为中断而抛出异常，它还是继续获取资源或者被挂起，也就是不对中断进行响应，忽略中断。

而带Interruptibly关键字的方法要对中断进行响应，其他线程中断了该线程，那么该线程会抛出`InterruptedException`异常而返回。

# AQS——条件变量的支持

`synchronized`内置锁有两个相匹配的方法来配合实现线程之间的同步——`notify`和`wait`。AQS也有两个条件变量——`signal`和`await`方法来配合锁的使用

它们的不同在于，`synchronized`只能同时和一个共享变量的`notidy`或`wait`方法实现同步，而AQS的一个锁可以对应多个条件变量。

到底什么是条件变量？观察下面代码

```java
public class AQSTest {
    public static void main(String[] args) {
        // 创建了一个独占锁ReentrantLock对象，ReentrantLock是基于AQS实现的锁。
        Lock lock = new ReentrantLock();
        // 创建了一个ConditionObject变量，这个变量就是Lock锁对应的一个条件变量。一个Lock可以创建多个条件变量
        Condition condition = lock.newCondition();

        //获取独占锁
        new Thread(()->{
            lock.lock();
            try{
                System.out.println("begin wait");
                //调用条件变量的await()方法阻塞挂起了当前线程。当其他线程调用条件变量的signal方法时，被阻塞的线程才会从await处返回
                condition.await();
                System.out.println("end wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try{
                System.out.println("begin signal");
                condition.signal();
                System.out.println("end signal");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
```

Lock对象等价于`synchronizer`加上共享变量，调用`lock.lock()`方法就相当于进入了`synchronizer`块，调用条件变量的`await()`方法相当于调用了共享变量的`wait()`方法。

条件变量是什么就呼之欲出了。

上面代码中`lock.newCondition()`作用其实是new了一个在AQS内部 声明的`ConditionObject`对象，`ConditionObject`是AQS的内部类，可以访问AQS内部的变量(例如状态变量`state`)和方法。再每个条件变量内部都维护了一个条件队列，用来存放调用条件变量的`await()`方法时被阻塞的线程。这个条件队列和AQS队列不是一回事。

## 探究条件变量原理

AQS队列的`await`源码如下：

```java
public final void await() throws InterruptedException {
    if (Thread.interrupted())
        throw new InterruptedException();
    //创建新的node节点，并插入到条件队列的末尾
    Node node = addConditionWaiter();
    // 标记位置
    // 加入条件队列后会释放lock时申请的资源，唤醒同步队列头节点
    int savedState = fullyRelease(node);
    int interruptMode = 0;
    // 调用park方法阻塞挂起当前线程
    // 确认node不在同步队列中，立马就被其他线程signal转移到同步队列
    // 线程之前在条件队列中沉睡，被唤醒之后加入到同步队列中
    while (!isOnSyncQueue(node)) {
        LockSupport.park(this);
        if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
            break;
    }
    if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
        interruptMode = REINTERRUPT;
    if (node.nextWaiter != null) // clean up if cancelled
        unlinkCancelledWaiters();
    if (interruptMode != 0)
        reportInterruptAfterWait(interruptMode);
}
```

当线程调用条件变量的`await()`方法时，再内部会构造一个`Node.CONDITION`的node节点，然后就该节点插入条件队列末尾，之后当前线程会释放获取的锁(也就是会操作所对应的state变量的值)，并被阻塞挂起。这时候如果有其他线程调用`lock.lock()`尝试获取锁，就会有一个线程获取到锁，如果获取到锁的线程调用了条件变量的`await()`方法，则该线程也会被放入条件变量的阻塞队列，然后释放获取到的锁，在await()方法处阻塞。

`ReentrantLock`类中的`signal()`方法实现如下：

```java
public final void signal() {
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    Node first = firstWaiter;
    if (first != null)
        doSignal(first);
}
```

当另外一个线程调用条件变量的`signal`方法时，在内部会把条件队列里面对头一个线程节点从条件队列里面移除并放入AQS阻塞队列里面，然后激活这个线程。

> 需要注意的是，AQS只提供ConditionObject的实现，并没有提供`newCondition`函数，该函数用来new一个ConditionObject对象。需要AQS的子类来提供。

## 条件队列和阻塞队列

上述代码很简单就能看出条件队列和阻塞队列的关系

1. 线程通过`lock.lock()`获取到实现AQS的锁，继续执行；
2. 未能获取到锁的线程，进入AQS的阻塞队列；
3. 已经获取到了锁的线程可以调用`await()`方法将自己挂起，释放锁，并进入条件队列。
4. 其他线程通过`signal()`方法唤醒`await()`的线程，被唤醒的线程进入阻塞队列准备争抢锁。

条件队列可以简单的看作是线程的**阻塞状态**，而阻塞队列可以看作是线程的**就绪状态**