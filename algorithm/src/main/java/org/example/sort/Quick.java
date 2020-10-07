package org.example.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.io.FileNotFoundException;

import static org.example.sort.Example.*;

/**
 * @ClassName Quick
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/7 21:59
 * @Version 1.0
 */
public class Quick<T> {
    public static <T>void sort(Comparable<T>[] a){
        StdRandom.shuffle(a);
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
        Comparable<T> v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (i == lo) break;
            if (i>=j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        assert isSorted(a);
        show(a);

    }
}
