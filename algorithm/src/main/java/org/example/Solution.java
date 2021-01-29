package org.example;

import org.example.leetcode.ListNode;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);
        solution.isPalindrome(node);
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode node = pHead;
        while (node != null) {
            RandomListNode copy = new RandomListNode(node.label);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }

        node = pHead;
        while (node != null) {
            RandomListNode copy = node.next;
            copy.random = node.random == null ? null : node.random.next;
            node = copy.next;
        }

        node = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (node != null) {
            RandomListNode copy = node.next;
            node.next = copy.next;
            copy.next = copy.next == null ? null : copy.next.next;
            node = copy.next;
        }
        return pCloneHead;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode halfOfEnd = halfOfEnd(head);
        halfOfEnd = reverseList(halfOfEnd);

        while (halfOfEnd != null) {
            if (halfOfEnd.val != head.val) return false;
            halfOfEnd = halfOfEnd.next;
            head = head.next;
        }
        return true;
    }

    public ListNode halfOfEnd(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.next;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode node = curr.next;
            curr.next = prev;
            prev = curr;
            curr = node;
        }
        return prev;
    }
}
