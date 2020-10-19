package org.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockExample
 * @Description 锁内存语义的具体实现机制
 * @Author yoveuio
 * @Date 2020/10/18 16:35
 * @Version 1.0
 */
public class ReentrantLockExample {

    int a = 0;
    Lock lock = new ReentrantLock();
    public void writer() {
        lock.lock();
        try {
            a++;
        } finally {
            lock.unlock();
        }
    }
    public void reader() {
        lock.lock();
        try {
            int i = a;
            System.out.println(i);
        } finally {
            lock.unlock();
        }
    }
}
