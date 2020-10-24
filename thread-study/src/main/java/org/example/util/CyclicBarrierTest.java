package org.example.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CyclicBarrierTest
 * @Description 同步屏障——CyclicBarrier
 *  等待所有线程到达屏障
 * @Author yoveuio
 * @Date 2020/10/24 14:17
 * @Version 1.0
 */
public class CyclicBarrierTest {

    public static CyclicBarrier c = new CyclicBarrier(2, new A());

    static class A implements Runnable{
        @Override
        public void run() {
            System.out.println(3);
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            try {
                c.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();

        try {
            c.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }
}
