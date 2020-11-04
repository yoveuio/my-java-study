package org.example.homework.work6;

import java.util.concurrent.*;

/**
 * @ClassName ConsumerProducer
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/29 10:17
 * @Version 1.0
 */
public class ConsumerProducer {
    private static Buffer buffer = new Buffer();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();
    }

    private static class ProducerTask implements Runnable {
        public void run() {
            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes " + i);
                    buffer.write(i++);
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class ConsumerTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\t\tConsumer reads " + buffer.read());
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static class Buffer {
        private static final int CAPACITY = 1; // buffer size
        private java.util.LinkedList<Integer> queue =
                new java.util.LinkedList<Integer>();
        // Create a new lock
        BlockingQueue<Integer> blockingDeque = new ArrayBlockingQueue<>(1000);

        public void write(int value) {
            //do some thing
            try {
                blockingDeque.put(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int read() {
            //do some thing
            int value = 0;
            try {
                value = blockingDeque.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value;
        }
    }
}
