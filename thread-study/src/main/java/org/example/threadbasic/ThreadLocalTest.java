package org.example.threadbasic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName ThreadLocal
 * @Description 保证线程安全，可以考虑使用ThreadLocal
 *  ThreadLocal在创建一个变量之后，访问这个变量的每个线程都会有这个变量的本地副本，线程实际操作的是这个本地副本
 * @Author yoveuio
 * @Date 2020/6/11 10:38
 * @Version 1.0
 */
public class ThreadLocalTest {

    /**
     * print函数
     */
    static void print(String str){

        //1.1 打印当前线程本地内存中LocalVariable变量的值
        System.out.println(str+":"+localVariable.get());
        //1.2 清楚当前线程本地内存中的localVariable变量
        localVariable.remove();
    }

    /**
     * 创建ThreadLocal变量
     */
    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        Unsafe unsafe = null;

        //创建线程one
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                //1) 设置线程one中本地变量localVariable的值
                /*set方法并不是将变量储存到ThreadLocal中，而是放入调用线程threadOne的threadLocals变量里面*/
                String strTest = "threadOne local variable";
                localVariable.set(strTest);
                //2） 调用打印函数
                print("threadOne");
                //3) 打印本地变量的值
                System.out.println("threadOne remove after"+":"+localVariable.get());
            }
        });

        //创建线程two
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                //1) 设置线程one中本地变量localVariable的值
                localVariable.set("threadTwo local variable");
                //2） 调用打印函数
                print("threadTwo");
                //3) 打印本地变量的值
                System.out.println("threadTwo remove after"+":"+localVariable.get());
            }
        });

        //启动线程
        threadOne.start();
        threadTwo.start();
    }

}
