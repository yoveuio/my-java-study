package org.example.threadbasic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName ThreadTest
 * @Description 创建线程的三种方式
 * @Author yoveuio
 * @Date 2020/6/9 14:18
 * @Version 1.0
 */
public class ThreadCreate {

    LinkedTransferQueue<String> linkedTransferQueue = null;

    /**
     * 继承实现多线程
     * 优点：直接使用this就可以调用线程，不需要使用Thread.currentThread()方法
     * 缺点：java不支持多继承，使用这个方法不能继承别的类了
     */
    public static class MyThread extends Thread {
        @Override
        public void run(){

            System.out.println("I am a child thread");

        }
    }


    /**
     * 接口实现多线程
     * 优点：可以继承其他类
     * 缺点：由于接口自身的问题，运行速度没有继承快。
     * 以上两个方法有一个共同的问题：任务没有返回值
     */
    public static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("I am a child thread");
        }

    }

    /**
     * FutureTask方式实现多线程
     * 类似实现Runnable接口
     *
     */
    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "I am a child thread";
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Integer a = 1;

        /* ------------------------------------------继承实现多线程-------------------------------------------- */
        MyThread myThread = new MyThread();
        //启动线程
        /*调用start方法之后，其实线程并没有马上执行，而是处于就绪状态
        此时，线程已经获取了除CPU之外所有的资源，等待获取CPU资源之后才真正处于运行状态*/
        myThread.start();

        /* ------------------------------------------实现接口方式实现多线程---------------------------------------- */
        RunnableTask runnableTask = new RunnableTask();

        new Thread(runnableTask).start();
        new Thread(runnableTask).start();

        /* ------------------------------------------FutureTask实现多线程-------------------------------------------- */
        //创建异步任务
        FutureTask<String> stringFutureTask = new FutureTask<>(new CallerTask());
        //启动线程
        new Thread(stringFutureTask).start();
        try{
            //等待任务执行完毕，得到结果
            String result = stringFutureTask.get();
            System.out.println(result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
