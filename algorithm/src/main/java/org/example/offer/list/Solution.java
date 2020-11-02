package org.example.offer.list;

import org.example.leetcode.ListNode;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/1 21:18
 * @Version 1.0
 */
public class Solution {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode sentinel, front, tail;
        tail = sentinel = new ListNode(-1);
        sentinel.next = head;
        front = head;
        while (front!=null) {
            if (front.val == val) {
                tail.next = front.next;
                break;
            }
            front = front.next;
            tail = tail.next;
        }

        return sentinel.next;
    }
}
