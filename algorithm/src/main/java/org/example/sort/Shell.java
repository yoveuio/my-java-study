package org.example.sort;


import java.io.FileNotFoundException;

import static org.example.sort.Example.*;

/**
 * @ClassName Shell
 * @Description 希尔排序的思想：
 *  使数组中任意间隔为h的元素都是有序的。这样的数组称为h有序数组
 *  换句话说：一个h有序数组就是h个互相独立的有序数组编织在一起组成的一个数组。
 * @Author yoveuio
 * @Date 2020/9/7 20:36
 * @Version 1.0
 */
public class Shell<T> {

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

    public static <T>void sort(Comparable<T>[] a) {
        int N = a.length;
        int h = 1;
        while (h < N/3) h=3*h+1; //1, 4, 13, 40, ...
        while (h >= 1) {
            for (int i=h; i<N; ++i) {
                for (int j=i; j>=h && less(a[j], a[j-h]); j-=h) {
                    exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        assert isSorted(a);
        show(a);

    }

}
