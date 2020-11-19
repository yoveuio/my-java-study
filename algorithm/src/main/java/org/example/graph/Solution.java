package org.example.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/19 19:16
 * @Version 1.0
 */
public class Solution {

    /**
     * 01矩阵：
     *  BFS:看到求最短容易想到最短路径(BFS)，这种多源点最短路径本质上可以想象成单源点最短路径。
     *      我们可以使用queue保存所有的源点，然后从源点开始遍历就可以了。而遍历得到的点又可以作为下一次开始的源点而加入队列。
     *
     * DP求解01矩阵
     *  DP:求最短距离除了BFS还可以使用DP。这道题也非常适合DP求解。
     *      每一个节点的状态都可以从`上下左右`四个方向得到。我们只需要从记录四个方向的状态值就可以了。四个方向又可以优化成左上+右下求解
     * public int[][] updateMatrix(int[][] matrix) {
     *     if (matrix == null || matrix.length == 0) return null;
     *     int m = matrix.length, n = matrix[0].length;
     *     int[][] dp = new int[m][n];
     *     //初始化
     *     for (int i = 0; i < m; i++) {
     *         Arrays.fill(dp[i], 10001);
     *     }
     *     for (int i = 0; i < m; i++) {
     *         for (int j = 0; j < n; j++) {
     *             if (matrix[i][j] == 0) dp[i][j] = 0;
     *         }
     *     }
     *     //状态转移
     *     //第一次填表从左上到右下
     *     for (int i = 0; i < m; i++) {
     *         for (int j = 0; j < n; j++) {
     *             if (i - 1 >= 0) {
     *                 dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
     *             }
     *             if (j - 1 >= 0) {
     *                 dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
     *             }
     *         }
     *     }
     *     //第二次填表从右下到左上
     *     for (int i = m - 1; i >= 0; i--) {
     *         for (int j = n - 1; j >= 0; j--) {
     *             if (i + 1 < m) {
     *                 dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
     *             }
     *             if (j + 1 < n) {
     *                 dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
     *             }
     *         }
     *     }
     *     return dp;
     * }
     *
     */
    public int[][] updateMatrix(int[][] matrix) {

        Queue<int[]> queue = new ArrayDeque<>();
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i=0; i<matrix.length; ++i) {
            for (int j=0; j<matrix[i].length; ++j) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
                else {
                    matrix[i][j] = -1;
                }
            }
        }

        int[][] forward = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i=0; i<4; ++i) {
                int dx = x + forward[i][0];
                int dy = y + forward[i][1];

                if (dx >= 0 && dx < m && dy >= 0 && dy < n && matrix[dx][dy] == -1) {
                    matrix[dx][dy] = matrix[x][y] + 1;
                    queue.add(new int[]{dx, dy});
                }
            }
        }
        return matrix;
    }
}
