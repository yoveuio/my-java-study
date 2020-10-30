package org.example.designPatterns.Single;

/**
 * @ClassName Test
 * @Description 单例模式：
 * @Author yoveuio
 * @Date 2020/9/27 17:42
 * @Version 1.0
 */
public class Test {

    static Test test;

    /**
     * 饿汉式，一般情况下使用
     */
    public static class Singleton {
        private static Singleton instance = new Singleton();
        private Singleton(){}
        private static Singleton getInstance(){
            return instance;
        }
    }

    /**
     * 懒汉式，线程不安全
     */
    public static class Singleton1 implements Runnable{
        private static Singleton1 instance;
        private int id;
        private Singleton1() {
        }

        public static Singleton1 getInstance() {
            if (instance == null) {
                instance = new Singleton1();
            }
            return instance;
        }

        @Override
        public void run() {
            for (int i = 0; i<100; i++) {
                System.out.println(Thread.currentThread().getName()+":"+id++);
            }
        }
    }

    /**
     * 懒汉式，线程安全
     * 缺点：对于频繁调用实例对象的程序来说效率低。对于99%的情况是不需要锁的
     */
    public static class Singleton2 {
        private static Singleton2 instance;
        private int id;
        private Singleton2() {
        }

        public static synchronized Singleton2 getInstance() {
            if (instance == null) {
                instance = new Singleton2();
            }
            return instance;
        }
    }

    /**
     * 懒汉式双锁机制
     * 注意实例对象应该加上volatile关键字,避免new Singleton初始化和引用之间的重排序
     */
    public static class Singleton3{
        private volatile static Singleton3 instance;
        private Singleton3(){

        }
        public static Singleton3 getInstance() {
            if (instance == null) {
                synchronized (Singleton3.class) {
                    if (instance == null) {
                        instance = new Singleton3();
                    }
                }
            }
            return instance;
        }
    }

    public static class Singleton4{
        private static class SingletonHandler{
            private static final Singleton4 instance = new Singleton4();
        }
        private Singleton4(){}
        public static Singleton4 getInstance(){
            return SingletonHandler.instance;
        }
    }

    /**
     * 设计到反序列化创建单例对象的时候可以考虑使用enum创建实例
     */
    public enum Singleton5 {
        INSTANCE;
        public void whateverMethod(){}
    }

    public static void main(String[] args) {
        Singleton1 instance = Singleton1.getInstance();
        new Thread(instance, "thread1").start();
        new Thread(instance, "Thread2").start();
        new Thread(instance, "Thread3").start();
    }

}
