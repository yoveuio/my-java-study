package org.example.backtracting;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 回溯
 * @date 2020/12/14 9:45
 */
public class Solution {

    /*Set<Integer> cols = new HashSet<>();
    Set<Integer> slashes1 = new HashSet<>();
    Set<Integer> slashes2 = new HashSet<>();*/
    List<Integer> queue = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row,    '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    private List<String> getNQueueAnswer(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            int queueIndex = queue.get(i);
            for (int j = 0; j < n; j++) {
                s.append(queueIndex==j ? 'Q' : '.');
            }
            list.add(s.toString());
        }
        return list;
    }
}
