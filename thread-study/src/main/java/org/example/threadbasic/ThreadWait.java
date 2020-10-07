package org.example.threadbasic;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

/**
 * @ClassName ThreadWait
 * @Description
 *  wait()
 *  wait(long timeout)
 *  wait(long timeout, int nanos)
 *  notify()
 *  notifyAll()
 * @Author yoveuio
 * @Date 2020/6/9 15:38
 * @Version 1.0
 */
public class ThreadWait {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        //创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA){
                        System.out.println("threadA get resourceA lock");
                        //获取resourceB共享资源的监视器锁
                        synchronized (resourceB){
                            System.out.println("threadA get resourceB lock");

                            //线程A阻塞，并释放获取到的resourceA的锁
                            /*如果线程被挂起之后不释放共享变量的锁，会处于死锁状态。
                            * 线程A挂起自己后释放共享变量上的锁，就是为了打破死锁的必要条件之一的持有并等待原则。*/
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                            System.out.println("threadA dead");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);

                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA){
                        System.out.println("threadB get resourceA lock");

                        System.out.println("threadB try get resourceB lock...");

                        resourceA.notifyAll();
                        //获取resourceB共享资源的监视器锁
                        synchronized (resourceB){
                            System.out.println("threadB get resourceB lock");

                            //线程B阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //启动线程
        threadA.start();
        threadB.start();

        //等待两个线程结束
        threadA.join();
        threadB.join();

        System.out.println("main over");
    }

}
