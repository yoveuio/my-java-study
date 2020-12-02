package org.example.dichotomize;

/**
 * @ClassName Solution
 * @Description 二分查找
 * @Author yoveuio
 * @Date 2020/11/30 9:50
 * @Version 1.0
 */
public class Solution {

    /**
     * @param nums 排序数组
     * @param target 目标值
     * @return 目标值的上界和下界, int[min][max]
     */
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


    /**
     * 求数组下界
     * @param n 数组长度
     * @param v 目标值
     * @param a 有序数组
     * @return 返回数组目标值的下界
     */
    public int upper_bound_(int n, int v, int[] a) {
        if (n == 0 || a[n - 1] < v) return n + 1;
        // write code here
        int lo = 0, hi = a.length - 1;
        int mid;
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