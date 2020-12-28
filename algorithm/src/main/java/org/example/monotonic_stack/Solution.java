package org.example.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 单调栈题目
 * @date 2020/12/17 17:07
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maximalRectangle(new char[][]{{'1'}});
        System.out.println(i);
    }

    /**
     * LC85. 最大矩形
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = (j == 0) ? 1 :  dp[i][j] + 1;
                }
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];
        int ans = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && dp[stack.peek()][j] >= dp[i][j]) {
                    stack.pop();
                }
                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && dp[stack.peek()][j] >= dp[i][j]) {
                    stack.pop();
                }
                right[i] = stack.isEmpty() ? n : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = 0; i < n; i++) {
                ans = Math.max(ans, (right[i] - left[i] - 1) * dp[i][j]);
            }
        }
        return ans;
    }

    /**
     * LC42. 接雨水
     * 给定n个非负整数表示每个宽度为1的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    public int trap(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }

    /**
     * 84. 柱状图中最大的矩形
     * 基本思路：
     *  从一个点开始，向左和向右扩展到不能扩展为止，为当前的最大矩形
     *  枚举所有的点，比较各个点扩展出来的最大矩形就可以求出整张图的最大矩形
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }
        return max;
    }

    /**
     * LC316. 去除重复字母
     */
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        Arrays.sort(num);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            num[ch - 'a']--;
            if (vis[ch - 'a']) continue;
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch &&
                    num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            vis[ch - 'a'] = true;
            sb.append(ch);
        }
        return sb.toString();
    }
}
