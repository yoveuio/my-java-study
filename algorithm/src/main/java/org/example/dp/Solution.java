package org.example.dp;

/**
 * @ClassName Solution
 * @Description 动态规划
 * @Author yoveuio
 * @Date 2020/10/26 8:47
 * @Version 1.0
 */
@SuppressWarnings("unused")
public class Solution {

    /**
     * LC746. 使用最小花费爬楼梯
     * 数组的每个索引作为一个阶梯，第i个阶梯对应着一个非负数的体力花费值cost[i](索引从0开始)。
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     *
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param cost 每上一层楼梯花费的体力
     * @return 返回花费的最少体力
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int dp1 = 0;
        int dp2 = 0;

        for (int i = 2; i <= n; i++) {
            int dp = Math.min(dp2 + cost[i - 1], dp1 + cost[i - 2]);
            dp1 = dp2;
            dp2 = dp;
        }
        return dp2;
    }

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
