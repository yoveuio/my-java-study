package org.example.threadbasic;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/16 14:50
 * @Version 1.0
 */
public class ThreadTest {

    int a = 0;
    boolean flag = false;
    public synchronized void writer() {
        a = 1;
        flag = true;
    }
    public synchronized void reader() {
        if (flag) {
            int i = a;
            System.out.println(i);
        }
    }

}
