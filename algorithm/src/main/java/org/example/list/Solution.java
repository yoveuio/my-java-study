package org.example.list;

import org.example.leetcode.ListNode;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/18 9:36
 * @Version 1.0
 */
public class Solution {

    /**
     * 双指针删除倒数第n个节点。
     * 其他方法：栈
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode first = sentinel, second = head;

        for (int i=1; i<n; ++i) {
            if (second == null) return sentinel.next;
            second = second.next;
        }

        while(second.next != null) {
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        return sentinel.next;
    }
}
