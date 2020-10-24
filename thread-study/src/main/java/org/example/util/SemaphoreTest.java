package org.example.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName SemaphoreTest
 * @Description Semaphore的应用场景
 * @Author yoveuio
 * @Date 2020/10/24 14:43
 * @Version 1.0
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        for (int i=0; i<THREAD_COUNT; ++i) {
            threadPool.execute(()->{
                try {
                    System.out.println(count.incrementAndGet() + ":save data");
                    s.acquire();
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}
