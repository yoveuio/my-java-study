package org.example.threadpool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolTest
 * @Description 线程池的管理和使用
 * @Author yoveuio
 * @Date 2020/10/20 20:09
 * @Version 1.0
 */
public class ThreadPoolTest {
    ExecutorService threadPoolExecutor;

    /**
     * 线程池类型
     */
    void Test(){
        threadPoolExecutor = Executors.newFixedThreadPool(5);
        threadPoolExecutor = Executors.newSingleThreadExecutor();
        threadPoolExecutor = Executors.newCachedThreadPool();
        threadPoolExecutor.execute(() -> {
            System.out.println(1);
        });
    }

    public static void main(String[] args) {

    }
}
