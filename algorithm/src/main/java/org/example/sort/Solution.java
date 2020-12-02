package org.example.sort;

import java.util.Arrays;

/**
 * @ClassName Solution
 * @Description 排序有关的题目
 * @Author yoveuio
 * @Date 2020/11/30 14:55
 * @Version 1.0
 */
public class Solution {

    /**
     * 快排思想找到TopK
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = partition(nums, lo, hi);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
    }

    // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int lt = lo, gt = hi+1;
        while (true) {
            while (++lt <= hi && nums[lt] < v);
            while (--gt >= lo && nums[gt] > v);
            if (lt >= gt) break;
            swap(nums, gt, lt);
        }
        nums[lo] = nums[gt];
        nums[gt] = v;
        return gt;
    }

    private void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
