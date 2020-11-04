package org.example.homework.aysn;

/**
 * @ClassName RunString
 * @Description 关于String方法的创建方式的问题
 * @Author yoveuio
 * @Date 2020/10/15 11:58
 * @Version 1.0
 */
class StringService {
    public void print(String stringParam) {
        try {
            synchronized (stringParam) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class StringThreadA extends Thread {
    private StringService stringservice;

    public StringThreadA(StringService s) {
        super();
        this.stringservice = s;
    }

    @Override
    public void run() {
        stringservice.print(new String("AA"));
    }
}

class StringThreadB extends Thread {
    private StringService stringservice;

    public StringThreadB(StringService s) {
        super();
        this.stringservice = s;
    }

    @Override
    public void run() {
        stringservice.print(new String("AA"));
    }
}

public class RunString {
    public static void main(String[] args) {
        StringService s = new StringService();
        StringThreadA a = new StringThreadA(s);
        StringThreadB b = new StringThreadB(s);
        a.setName("A");
        a.start();
        b.setName("B");
        b.start();
    }
}

