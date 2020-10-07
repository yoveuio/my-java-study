package org.example.test;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName test
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/22 17:45
 * @Version 1.0
 */
public class DelayQueueDemo {
    public static void main(String args[]) throws InterruptedException {

        TaskQueueDaemonThread instance = TaskQueueDaemonThread.getInstance();

        instance.init();
        instance.put(new Task(TimeUnit.NANOSECONDS.convert(10, TimeUnit.SECONDS), DelayQueueDemo::print));
        instance.put(new Task(TimeUnit.NANOSECONDS.convert(11, TimeUnit.SECONDS), DelayQueueDemo::print));
        instance.put(new Task(TimeUnit.NANOSECONDS.convert(12, TimeUnit.SECONDS), DelayQueueDemo::print));
        instance.put(new Task(TimeUnit.NANOSECONDS.convert(13, TimeUnit.SECONDS), DelayQueueDemo::print));
        instance.put(new Task(TimeUnit.NANOSECONDS.convert(14, TimeUnit.SECONDS), DelayQueueDemo::print));
        instance.put(new Task(TimeUnit.NANOSECONDS.convert(15, TimeUnit.SECONDS), DelayQueueDemo::print));

    }

    public static void print() {
        System.out.println("hello world. handle time "+System.currentTimeMillis());
    }

}

/**
 * 定义任务类
 */
class Task<T extends Runnable> implements Delayed{
    private T actualTask;
    //过期时间
    private long time;
    private int order;
    private final static AtomicInteger atomic = new AtomicInteger();

    public Task(long timeOut,T actualTask) {
        super();
        this.actualTask = actualTask;
        time = System.nanoTime()+timeOut;
        order = atomic.getAndIncrement();
    }

    @Override
    public int compareTo(Delayed o) {
        if(this == o) {
            return 0;
        }
        if(o instanceof Task) {
            Task otherTask = (Task) o;
            long diff = this.time - otherTask.time;
            if(diff>0) {
                return 1;
            }else if(diff <0) {
                return -1;
            }else if(this.order > otherTask.order) {
                return 1;
            }else {
                return -1;
            }
        }
        long d = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return d==0? 0:(d>0?1:-1);
    }



    public Runnable getActualTask() {
        return actualTask;
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int hashCode() {
        return actualTask.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            return object.hashCode() == hashCode() ? true : false;
        }
        return false;
    }
}

/**
 * 定义任务调度类
 */
class TaskQueueDaemonThread {
    private DelayQueue<Task> queue = new DelayQueue();
    private ExecutorService executor = Executors.newFixedThreadPool(20);
    private static final TaskQueueDaemonThread instance = new TaskQueueDaemonThread();

    private TaskQueueDaemonThread () {}

    public static TaskQueueDaemonThread getInstance() {
        return instance;
    }

    public void init() {
        Thread t = new Thread(this::execute);
        t.setName("TaskQueueDaemonThread");
        t.setDaemon(false);
        t.start();
    }

    private void execute() {
        while(true) {
            try {
                //从延迟队列中取值,如果没有对象过期则队列一直等待，
                Task t = queue.take();
                if(t != null) {
                    Runnable actualTask = t.getActualTask();
                    if (actualTask == null) {
                        continue;
                    }
                    executor.execute(actualTask);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void put(Task t) {
        queue.put(t);
    }

}