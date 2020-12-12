package org.example.sreach;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description TODO
 * @date 2020/12/9 19:18
 */
public class Solution {

    public int uniquePaths(int m, int n) {
        int[][] map = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) map[i][j] = 1;
                else map[i][j] = map[i][j-1] + map[i-1][j];
            }
        }
        return map[m][n];
    }
}
