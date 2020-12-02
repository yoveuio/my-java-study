package org.example.dp;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/26 8:47
 * @Version 1.0
 */
public class Solution {

    /**
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * @param nums 整形数组
     * @return 返回子数组和的最大值
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = dp[len] = nums[0];
        for (int i=1; i<len; i++) {
            if (dp[i-1] < 0) {
                dp[i] = nums[i];
            }
            else {
                dp[i] = dp[i-1]+nums[i];
            }
            dp[len] = Math.max(dp[i], dp[len]);
        }
        return dp[len];
    }

    /**
     * 青蛙跳台阶
     * @param n
     * @return
     */
    public int numWays(int n) {
        int a = 1, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return a;
    }
}
