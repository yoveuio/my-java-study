package org.example.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName LockTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/6/23 9:51
 * @Version 1.0
 */
public class LockTest {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread>  waiters = new ConcurrentLinkedDeque<>();

    public void lock(){
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

//        只有队首的线程可以获取锁
        while(waiters.peek() != current || !locked.compareAndSet(false, true)){
            LockSupport.park(this);
            if (Thread.interrupted()){
                wasInterrupted = true;
            }
        }
    }

}
