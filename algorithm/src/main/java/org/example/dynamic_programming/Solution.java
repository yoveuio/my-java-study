package org.example.dynamic_programming;

/**
 * @ClassName Solution
 * @Description 动态规划系列题目
 * @Author yoveuio
 * @Date 2020/11/9 19:25
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = solution.translateNum(624);
        System.out.println(num);
    }

    /**
     * 斐波那契
     */
    public int translateNum(int num) {
        char[] chs = String.valueOf(num).toCharArray();
        int len = chs.length;
        if (chs.length <= 1) return 1;
        int[] dp = new int[chs.length];
        dp[0] = 1;
        dp[1] = twoNumberIsLetter(chs[0], chs[1]) ? 2 : 1;
        for (int i = 2; i < len; i++) {
            dp[i] = dp[i - 1] + (twoNumberIsLetter(chs[i - 1], chs[i]) ? dp[i - 2] : 0);
        }
        return dp[len - 1];
    }

    private boolean twoNumberIsLetter(char c1, char c2) {
        return c1 != '0' && (c1 - '0') * 10 + (c2 - '0') < 26 && (c1 - '0') * 10 + (c2 - '0') >= 0;
    }

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
