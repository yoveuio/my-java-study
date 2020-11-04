package org.example.homework.work1;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/10 11:35
 * @Version 1.0
 */
public class ThreadTest {

    static class Print implements Runnable{
        int sum;
        @Override
        public void run() {

            sum = 0;
            for (int i=1; i<=100; ++i) {
                sum += i;
            }
            System.out.println(sum);
        }
    }
    static class PrintA implements Runnable {
        @Override
        public void run() {
            for (int i=1; i<=100; ++i) {
                System.out.println('a');
                if (i == 50) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class PrintB implements Runnable {
        @Override
        public void run() {
            for (int i=1; i<=100; ++i) {
                System.out.println('b');
                if (i == 50) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Print print = new Print();
        PrintA printA = new PrintA();
        PrintB printB = new PrintB();

        Thread thread = new Thread(print);
        Thread threadA = new Thread(printA);
        Thread threadB = new Thread(printB);

        thread.setPriority(Thread.MAX_PRIORITY);
        threadB.setPriority(Thread.MIN_PRIORITY);

        threadA.start();
        threadB.start();
        threadB.join();
        thread.start();
    }
}
