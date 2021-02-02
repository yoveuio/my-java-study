package org.example;

import java.util.ArrayList;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
public class Solution {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k > input.length || k < 0 || input.length == 0) return new ArrayList<>();
        return getKLeast(input, 0, input.length-1, k - 1);
    }

    private ArrayList<Integer> getKLeast(int[] input, int l, int r, int k) {
        int j = partition(input, l, r);
        if (j == k) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i <= j; i++) {
                list.add(input[i]);
            }
            return list;
        }
        return j > k ? getKLeast(input, l, j - 1, k) : getKLeast(input, j + 1, r, k);
    }

    private int partition(int[] nums, int l, int r) {
        int lo = l, hi = r + 1;
        int v = nums[lo];
        while (true) {
            while (++lo <= r && nums[lo] < v);
            while (--hi > l && nums[hi] > v);
            if (lo >= hi) break;
            swap(nums, lo, hi);
        }
        swap(nums, l, hi);
        return hi;
    }

    public void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
