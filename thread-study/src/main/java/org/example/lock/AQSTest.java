package org.example.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName AQSTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/18 21:35
 * @Version 1.0
 */
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
