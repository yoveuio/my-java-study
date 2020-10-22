package homework.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/22 10:22
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        try {
            DealThread t1 = new DealThread();
            t1.setFlag("a");
            Thread thread1 = new Thread(t1);
            thread1.start();
            Thread.sleep(100);
            t1.setFlag("b");
            Thread thread2 = new Thread(t1);
            thread2.start();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
    }
}

class DealThread implements Runnable {
    public String username;
    public Lock lock = new ReentrantLock();
    public Condition lock1 = lock.newCondition();
    public Condition lock2 = lock.newCondition();
    public boolean flag = false;

    public void setFlag(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if (username.equals("a")) {
            lock.lock();
            try {
                while (!flag) lock1.await();
                System.out.println("username = " + username);
                Thread.sleep(3000);
                System.out.println("按lock1->lock2代码顺序执行了");
                lock2.signalAll();
                flag = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        if (username.equals("b")) {
            lock.lock();
            try {
                while (flag) lock2.await();
                System.out.println("username = " + username);
                Thread.sleep(3000);
                System.out.println("按lock2->lock1代码顺序执行了");
                lock1.signalAll();
                flag = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
