package org.example.threadbasic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName ThreadUnsafe
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/6/13 9:43
 * @Version 1.0
 */
public class ThreadUnsafe {
    /**
     * 获取Unsafe的实例
     * Unsafe必须使用Bootstrap类加载器加载，直接使用因为main函数使用AppClassLoader加载
     * 会报错，这时我们可以使用反射机制来获取Unsafe实例
     */
    static final Unsafe UNSAFE;

    /**
     * 记录变量state在类ThreadUnsafe中的偏移地址
     */
    static final long STATE_OFFSET;

    /**
     * 变量
     */
    private volatile long state = 0;

    long get(){
        return state;
    }

    static {
        try{


            //使用反射获取Unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");

            //设置为可存取
            field.setAccessible(true);

            //获取该变量的值
            UNSAFE = (Unsafe) field.get(null);

            //获取state变量在类中的偏移地址
            STATE_OFFSET = UNSAFE.objectFieldOffset(ThreadUnsafe.class.
                    getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        //创建实例
        ThreadUnsafe testUnsafe = new ThreadUnsafe();
        Boolean success = UNSAFE.compareAndSwapInt(testUnsafe, STATE_OFFSET,
                0, 1);
        System.out.println(success+":"+testUnsafe.get());
    }

}
