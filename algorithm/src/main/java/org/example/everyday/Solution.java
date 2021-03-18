package org.example.everyday;

import org.example.leetcode.ListNode;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 每日一题
 * @date 2021/3/17 8:38
 */
@SuppressWarnings("unused")
public class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode prev = sentinel;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
            head = head.next;
        }
        ListNode tail = head;
        for (int i = left; i < right; i++) {
            tail = tail.next;
        }
        ListNode[] listNodes = reverseList(head, tail);
        prev.next = listNodes[0];
        return sentinel.next;
    }

    public ListNode[] reverseList(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode curr = head;
        while (prev != tail) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return new ListNode[]{tail, head};
    }

    public int numDistinct(String s, String t) {
        int n = t.length() + 1, m = s.length() + 1;
        int[][] dp = new int[n][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
