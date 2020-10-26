package org.example.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ExchangerTest
 * @Description Exchanger应用场景
 * @Author yoveuio
 * @Date 2020/10/24 15:07
 * @Version 1.0
 */
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(()->{
            try {
                String A = "银行流水";
                exgr.exchange(A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.execute(()->{
            try {
                String B = "银行流水B";
                String A = exgr.exchange(B);
                System.out.println("A和B数据是否一直:" + A.equals(B));
                System.out.println("A录入的是:" + A);
                System.out.println("B录入的是:" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
    }
}
