package org.example.lock;


/**
 * @ClassName DoubleCheckedLocking
 * @Description 双重锁定
 * @Author yoveuio
 * @Date 2020/10/20 16:55
 * @Version 1.0
 */
public class DoubleCheckedLocking {
    //这种写法是有问题的，如果instance的初始化和赋值操作重排序，会出现一个线程访问到了未被正确初始化的对象
    private static DoubleCheckedLocking instance;

    public static DoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) instance = new DoubleCheckedLocking();
            }
        }
        return instance;
    }
}
