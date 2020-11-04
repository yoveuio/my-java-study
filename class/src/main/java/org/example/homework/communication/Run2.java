package org.example.homework.communication;

/**
 * @ClassName Run2
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/22 15:08
 * @Version 1.0
 */
public class Run2 {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract sub = new Subtract(lock);
        ThreadAdd addThread = new ThreadAdd(add);
        ThreadSubtract subThread1 = new ThreadSubtract(sub);
        subThread1.setName("subtract1Thread");
        ThreadSubtract subThread2 = new ThreadSubtract(sub);
        subThread2.setName("subtract2Thread");
        subThread1.start();
        subThread2.start();
        Thread.sleep(1000);
        addThread.start();
    }
}
