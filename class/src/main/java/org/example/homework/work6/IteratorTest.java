package org.example.homework.work6;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName IteratorTest
 * @Description 测试HashSet的快速失败
 * @Author yoveuio
 * @Date 2020/10/29 10:51
 * @Version 1.0
 */
public class IteratorTest {
    static Set<Integer> set = new HashSet<>();
    static Lock lock = new ReentrantLock();
    static Condition write = lock.newCondition();
    static Condition read = lock.newCondition();
    static boolean flag = false;


    public static void main(String[] args) {
        IteratorTest iteratorTest = new IteratorTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        if (!flag) read.await();
                        for (Integer next : set) {
                            System.out.println(next);
                        }
                        write.signalAll();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    lock.lock();
                    try {
                        if (flag) write.await();
                        set.add(count++);
                        read.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        executorService.shutdown();
    }
}

