package org.example.sort;

import edu.princeton.cs.algs4.StdRandom;
import sun.plugin.com.event.COMEventHandler;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.example.sort.Example.*;

/**
 * @ClassName Quick
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/7 21:59
 * @Version 1.0
 */
public class Quick<T> {

    private static <T>void quick3waySort(Comparable<T>[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo +1, gt = hi;
        Comparable<T> v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo((T) v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    public static <T>void sort(Comparable<T>[] a){
        //StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static <T>void sort(Comparable<T>[] a, int lo, int hi) {
        if (hi<=lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static <T>int partition(Comparable<T>[] a, int lo, int hi) {
        int i=lo, j=hi+1;
        //数组[lo,i]都小于v, [j, hi]都大于v
        Comparable<T> v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i>=j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        Integer[] a1 = {5, 2, 3, 1};
        quick3waySort(a1, 0, a1.length-1);
        assert isSorted(a1);
        show(a1);
    }
}
