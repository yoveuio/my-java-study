package org.example.offer;

import edu.princeton.cs.algs4.In;
import javafx.util.Pair;

import java.util.*;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/17 14:58
 * @Version 1.0
 */
public class Solution {

    /**
     * 最大的整数次方
     *  快速幂：
     *      通过二分/二进制的性质，求x^n
     *          n = f(n) = 1*b1 + 2*b2 + …… + 2^(m-1)*bm
     *          其中
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

    /**
     * 广度优先遍历BFS
     *  机器人的运动范围
     * @param m 行
     * @param n 列
     * @param k 运动范围
     * @return 能到达的格子数
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] vis = new boolean[m][n];
        int ans = 1;
        vis[0][0] = true;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }
                // 边界判断
                if (i - 1 >= 0) {
                    vis[i][j] |= vis[i - 1][j];
                }
                if (j - 1 >= 0) {
                    vis[i][j] |= vis[i][j - 1];
                }
                ans += vis[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    /**
     * 找到数组中的第一个重复数字
     * 进阶考点：原地Hash
     * @param nums 给定的一个数组
     * @return 任意一个重复数字
     */
    public int findRepeatNumber(int[] nums) {
/*        Set<Integer> set = new HashSet<>();

        for (int i: nums) {
            if (!set.contains(i)) {
                set.add(i);
            }
            else return i;
        }
        throw new RuntimeException();*/
        int temp;
        for (int i=0; i<nums.length; ++i) {
            while(nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }


    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }
}
