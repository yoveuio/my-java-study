package org.example.divide_and_conquer;

import org.example.leetcode.ListNode;

/**
 * @ClassName Solution
 * @Description 分治
 * @Author yoveuio
 * @Date 2020/10/12 16:33
 * @Version 1.0
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(0), cur = sentinel;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return sentinel.next;
    }
}