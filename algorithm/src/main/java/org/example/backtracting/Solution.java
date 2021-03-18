package org.example.backtracting;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 回溯
 * @date 2020/12/14 9:45
 */
@SuppressWarnings("unused")
public class Solution {
    List<String> stringListAnswer;

    /**
     * 22. 括号生成
     */
    public List<String> generateParenthesis(int n) {
        stringListAnswer = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        _generateParenthesis(b, n, 0, 0);
        return stringListAnswer;
    }

    private void _generateParenthesis(StringBuilder b, int n, int left, int right) {
        if (b.length() == n * 2) {
            stringListAnswer.add(b.toString());
            return ;
        }

        if (left < n) {
            b.append('(');
            _generateParenthesis(b, n, left + 1, right);
            b.deleteCharAt(b.length() - 1);
        }

        if (right < left) {
            b.append(')');
            _generateParenthesis(b, n, left, right + 1);
            b.deleteCharAt(b.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

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
        List<String> board = new ArrayList<>();
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
