package org.example.dynamic_programming;

/**
 * @ClassName Solution
 * @Description 动态规划系列题目
 * @Author yoveuio
 * @Date 2020/11/9 19:25
 * @Version 1.0
 */
@SuppressWarnings("unused")
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean aa = solution.isMatch("aa", "a*");
        System.out.println(aa);
    }

    /**
     * 72. 编辑距离
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] - 1, Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 55. 跳跃游戏
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;

        int step = 0;
        for (int i = 0; i < n; i++) {
            step = Math.max(step, nums[i] + i);
            if (step < i || (step == i && i != n - 1)) return false;
        }
        return true;
    }

    /**
     * 10. 正则表达式匹配
     */
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // i = 0是 p = "a*"这样，通配符出现在第二个的做准备
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (match(s, p, i - 1, j - 2)) {
                        dp[i][j] = dp[i][j] | dp[i - 1][j];
                    }
                }
                else if (match(s, p, i - 1, j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public boolean match(String s, String p, int i, int j) {
        if (i < 0) return false;
        if (p.charAt(j) == '.') return true;
        return s.charAt(i) == p.charAt(j);
    }

    /**
     * 5. 最长回文子串
     * @param s 给你一个字符串 s，找到 s 中最长的回文子串
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;
        int begin = 0, max = 1;

        boolean[][] dp = new boolean[n][n];
        for (int len = 0; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                    continue;
                }
                if (j - i < 3) dp[i][j] = true;
                else dp[i][j] = dp[i + 1][j - 1];
                if (dp[i][j] && j - i + 1 > max) {
                    begin = i;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    /**
     * LC221. 最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != '1') continue;
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }

    /**
     * LC152. 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 分正负讨论
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        int[] min = new int[n];
        int[] max = new int[n];

        min[0] = nums[0];
        max[0] = nums[0];

        for (int i = 1; i < n; i++) {
            max[i] = Math.max(max[i - 1] * nums[i], Math.max(min[i - 1] * nums[i], nums[i]));
            min[i] = Math.min(min[i - 1] * nums[i], Math.min(max[i - 1] * nums[i], nums[i]));
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(max[i], ans);
        }
        return ans;
    }

    /**
     * LC198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i -  1]);
        }

        return Math.max(dp[n - 1], dp[n - 2]);
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     */
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        if (n < 1) return 0;
        int sell = 0;
        int buy = -prices[0];

        for (int price : prices) {
            buy = Math.max(buy, -price);
            sell = Math.max(sell, buy + price);
        }
        return sell;
    }

    /**
     * LC121 买卖股票的最佳时机
     *
     * 满足单调栈应用场景：需要高效率查询某个位置左右两侧比他大（或小）的数的位置
     * @param prices 股票的价格
     * @return 返回最佳的天数
     */
    public int maxProfit(int[] prices) {
        int dp1 = 0, dp2 = -prices[0];


        for (int i = 1; i < prices.length; i++) {
            dp1 = Math.max(dp2 + prices[i], dp1);
            dp2 = Math.max(-prices[i], dp2);
        }
        return dp1;
    }

    /**
     * LC188. 买卖股票的最佳时机 IV
     * 给定一个整数数组prices ，它的第i个元素prices[i]是一支给定的股票在第i天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成k笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // 第i天买入
        int[] dp1 = new int[k + 1];
        // 第i天卖出
        int[] dp2 = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            dp1[i] = -prices[0];
        }

        for (int price : prices) {
            for (int j = 1; j <= k; j++) {
                // 买入
                dp1[j] = Math.max(dp1[j], dp2[j - 1] - price);
                dp2[j] = Math.max(dp2[j], dp1[j] + price);
            }
        }
        return dp2[k];
    }

    /**
     * LC714 买卖股票的最佳时机含手续费
     * 给定一个整数数组prices，其中第i个元素代表了第i天的股票价格 ；非负整数fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
     * @param prices 股票的价格
     * @param fee 手续费
     * @return 返回最大的利润
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        // dp1不买，dp2买入
        int dp1, dp2;
        dp1 = 0;
        dp2 = -prices[0];

        for (int i = 1; i < len; i++) {
            dp1 = Math.max(dp2 + prices[i] - fee, dp1);
            dp2 = Math.max(dp1 - prices[i], dp2);
        }
        return dp1;
    }

    /**
     * LC122 买卖股票的最佳时机Ⅱ
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *      // 未压缩空间
     *      public int maxProfit2(int[] prices) {
     *         int len = prices.length;
     *         // dp[i][0]代表第i天不持有股票的最大利润
     *         // dp[i][1]代表第i天持有股票的最大利润
     *         int[][] dp = new int[len][2];
     *         // 第0天不买
     *         dp[0][0] = 0;
     *         // 第0天买股票
     *         dp[0][1] = -prices[0];
     *
     *         for (int i = 1; i < len; i++) {
     *             // 第i天卖出, 获得的最大利润
     *             dp[i][0] = Math.max(prices[i] + dp[i - 1][1], dp[i - 1][0]);
     *             // 第i天买入, 获得的最大利润
     *             dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
     *         }
     *         return dp[len - 1][0];
     *     }
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
     * @param prices 股票价钱
     * @return 返回最大的利润
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int dp1 = 0, dp2 = -prices[0];

        for (int i = 1; i < len; i++) {
            // 第i天卖出, 获得的最大利润
            dp1 = Math.max(dp2 + prices[i], dp1);
            // 第i天买入, 获得的最大利润
            dp2 = Math.max(dp1 - prices[i], dp2);
        }
        return dp1;
    }

    /**
     * LC123. 买卖股票的最佳时机 III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
     * 注意:你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 维护初始、买入1、卖出1、买入2、卖出2五种状态。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
     */
    public int maxProfit3(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        //定义5种状态，并初始化第一天的状态
        int dp1 = -prices[0];
        int dp2 = 0;
        int dp3 = -prices[0];
        int dp4 = 0;
        for(int i=1;i<n;++i) {
            //处理第一次买入、第一次卖出
            dp1 = Math.max(dp1,-prices[i]);
            dp2 = Math.max(dp2,dp1+prices[i]);
            //处理第二次买入、第二次卖出
            dp3 = Math.max(dp3,dp2-prices[i]);
            dp4 = Math.max(dp4,dp3+prices[i]);
        }
        //返回最大值
        return dp4;
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
