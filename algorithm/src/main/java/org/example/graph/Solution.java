package org.example.graph;

import java.util.*;

/**
 * @ClassName Solution
 * @Description 有关题的题目
 * @Author yoveuio
 * @Date 2020/11/19 19:16
 * @Version 1.0
 */
@SuppressWarnings("unused")
public class Solution {

    public int orangesRotting(int[][] grid) {
        int count = 0;
        final int[][] forward = new int[][] {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}};
        if (grid == null || grid.length == 0) return -1;
        class Point {
            final int x;
            final int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Deque<Point> deque = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) deque.add(new Point(i, j));
                else if (grid[i][j] == 1) count++;
            }
        }
        if (count == 0 ) return 0;

        int ans = 0;
        while (!deque.isEmpty()) {
            for (int i = deque.size(); i > 0; i--) {
                Point point = deque.poll();
                for (int j = 0; j < 4; j++) {
                    assert point != null;
                    int x = point.x + forward[j][0];
                    int y = point.y + forward[j][1];
                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        deque.add(new Point(x, y));
                        grid[x][y] = 2;
                        count--;
                    }
                }
            }
            ans++;
        }
        return count == 0 ? ans - 1 : -1;
    }


    /**
     * LC48 旋转图像
     * 将图像顺时针旋转 90 度。
     * 说明：
     *  你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     * 链接：https://leetcode-cn.com/problems/rotate-image/
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 剑指 Offer 47. 礼物的最大价值
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
     * @param grid 图礼物
     * @return 返回图中一条路径的礼物的最大值
     */
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != 0 || j != 0) {
                    grid[i][j] = Math.max(i != 0 ? grid[i-1][j] : 0, j != 0 ? grid[i][j-1] : 0) + grid[i][j];
                }
            }
        }
        return grid[n - 1][m - 1];
    }

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
