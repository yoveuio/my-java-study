package org.example;

import com.sun.istack.internal.NotNull;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 多线程有关的题目
 * @date 2021/2/24 21:15
 */
@SuppressWarnings("unused")
public class Solution {

    static ExecutorService executor;
    final static Map<Integer, Thread> map = new ConcurrentHashMap<>();

    public static void printNum(int num, String nums) throws InterruptedException {
        executor = new ThreadPoolExecutor(num, num, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(100),
                new ThreadFactory() {
                    final AtomicInteger count = new AtomicInteger(0);

                    @Override
                    public Thread newThread(@NotNull Runnable r) {
                        Thread thread = new Thread(r);
                        int threadName = count.getAndIncrement();
                        thread.setName(String.valueOf(threadName));
                        map.put(threadName, thread);
                        return thread;
                    }
                });

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                Thread t = Thread.currentThread();
                int count = 10;
                while (count-- > 0) {
                    LockSupport.park();
                    System.out.println(t.getName());
                }
            });

        }

        for (int i = 0; i < nums.length(); i++) {
            LockSupport.unpark(map.get(nums.charAt(i) - '0'));
            Thread.sleep(100L);
        }
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        printNum(9, "3382019835830");
    }
}
