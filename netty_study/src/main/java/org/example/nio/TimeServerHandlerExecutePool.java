package org.example.nio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TimeServerHandlerExecutePool
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/25 20:11
 * @Version 1.0
 */
public class TimeServerHandlerExecutePool {

    private final ExecutorService executor;

    public TimeServerHandlerExecutePool (int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }
}
