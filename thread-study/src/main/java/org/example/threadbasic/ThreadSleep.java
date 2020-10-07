package org.example.threadbasic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ThreadSleep
 * @Description Thread类中的sleep方法可以让线程暂时让出指定时间的执行权，也就是这段时间不参与CPU的调度
 *  但是该线程所拥有的监视器资源，比如锁还是持有不让出的。指定的睡眠时间到了后该函数会正常返回，参与CPU的调度
 *  如果在睡眠期间其他线程调用了该线程的interrupt方法，会抛出InterruptedException异常而返回
 * @Author yoveuio
 * @Date 2020/6/10 22:33
 * @Version 1.0
 */
public class ThreadSleep {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        //创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try{
                    System.out.println("child ThreadA is in sleep");

                    Thread.sleep(10000);

                    System.out.println("child ThreadA is in awaken");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        });

        //创建线程B
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try{
                    System.out.println("child ThreadB is in sleep");

                    Thread.sleep(10000);

                    System.out.println("child ThreadB is in awaken");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        });

        threadA.start();
        threadB.start();

    }

}
