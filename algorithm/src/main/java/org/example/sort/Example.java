package org.example.sort;

import java.io.FileNotFoundException;

/**
 * @ClassName Example
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/7 21:02
 * @Version 1.0
 */
public class Example {

    //判断相隔为h的数组是否满足条件
    public static <T>boolean less(Comparable<T> v, Comparable<T> w){
        return v.compareTo((T) w) < 0;
    }
    //交换
    public static <T> void exch(Comparable<T>[] a, int i, int j){
        Comparable<T> t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static <T> void show(Comparable<T>[] a){
        //在单行中打印数组
        for (Comparable<T> tComparable : a) {
            System.out.print(tComparable + "");
            System.out.println();
        }
    }
    public static <T> boolean isSorted(Comparable<T>[] a){
        //测试数组元素是否有序
        for (int i = 0; i < a.length; i++) {
            if(less(a[i], a[i-1]))
                return false;
        }
        return true;
    }

}
