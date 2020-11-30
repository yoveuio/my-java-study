package org.example.dichotomize;

/**
 * @ClassName Solution
 * @Description 二分查找
 * @Author yoveuio
 * @Date 2020/11/30 9:50
 * @Version 1.0
 */
public class Solution {
    public int upper_bound_ (int n, int v, int[] a) {
        if(n==0 || a[n-1] < v) return n+1;
        // write code here
        int lo = 0, hi = a.length-1;
        int mid = -1;
        while (lo < hi) {
            mid = (hi - lo >> 1) + lo;
            if (a[mid] >= v) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi + 1;
    }
}
