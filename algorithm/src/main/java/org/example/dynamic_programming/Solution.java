package org.example.dynamic_programming;

/**
 * @ClassName Solution
 * @Description 动态规划系列题目
 * @Author yoveuio
 * @Date 2020/11/9 19:25
 * @Version 1.0
 */
public class Solution {

    /**
     * 最长上升子序列
     * 动态规划：
     * if (nums.length == 0) {
     *     return 0;
     * }
     * int len = nums.length;
     * int[] dp = new int[nums.length];
     *
     * int maxN = 1;
     * for (int i=0; i<len; ++i) {
     *     dp[i] = 1;
     *     for (int j=0; j<i; ++j) {
     *         if (nums[i] > nums[j])
     *             dp[i] = Math.max(dp[j]+1, dp[i]);
     *     }
     *     maxN = Math.max(dp[i], maxN);
     * }
     * return maxN;
     *
     * 二分+贪心解法如下：
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //保证dp数组是一个有序数组，里面保存着最长上升子序列，dp[i]代表着长度为i的最长子序列中下标最小的元素(贪心算法:使得子序列上升最慢)
        int[] dp = new int[nums.length+1];
        dp[1] = nums[0];

        int len = 1;
        for (int num : nums) {
            if (dp[len] < num) {
                dp[++len] = num;
            } else {
                int l = 0, r = len, pos = 0;
                //找到第一个小于num的元素，将其替换
                while (l <= r) {
                    int mid = ((r - l) >> 1) + l;
                    if (dp[mid] < num) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                dp[pos + 1] = num;
            }
        }
        return len;
    }
}
