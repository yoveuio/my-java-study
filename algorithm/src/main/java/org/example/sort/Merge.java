package org.example.sort;

/**
 * @ClassName Merge
 * @Description 归并排序
 * @Author yoveuio
 * @Date 2020/11/21 10:04
 * @Version 1.0
 */
public class Merge{
    //归并需要的辅助数组
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = (hi - lo >> 1) + lo;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k < hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = a[i++];
            else if (Example.less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[j++];
        }
    }
}
