package org.example;

import org.example.leetcode.ListNode;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode == null) return null;

        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        while (pNode.next != null) {
            TreeLinkNode node = pNode.next;
            if (node.left == pNode) {
                return node;
            }
            pNode = pNode.next;
        }
        return null;
    }


    public ListNode deleteDuplication(ListNode pHead) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = pHead;
        ListNode prev = sentinel;
        ListNode curr = pHead;

        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                prev.next = curr;
                curr = curr.next;
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }
        return sentinel.next;
    }

    public ListNode EntryNodeOfLoop(ListNode pHead){
        ListNode fast = pHead;
        ListNode slow = pHead;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}
