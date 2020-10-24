package org.example.util;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Description 等待多线程完成——CountDownLatch
 * @Author yoveuio
 * @Date 2020/10/24 14:01
 * @Version 1.0
 */
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println(1);
            c.countDown();
            System.out.println(2);
            c.countDown();
        }).start();

        c.await();
        System.out.println("3");
    }
}
