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
