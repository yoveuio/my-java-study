package org.example.list;

import org.example.leetcode.ListNode;

import java.util.*;

/**
 * @ClassName Solution
 * @Description 有关列表的算法
 * @Author yoveuio
 * @Date 2020/10/18 9:36
 * @Version 1.0
 */
public class Solution {

    /**
     * 通过hash公式
     * hash = a[1]*seed^0 + ... + a[n]*seed^n-1
     * 由于是回文数组，逆序链表的hash应该是相同的
     * @param head 链表头节点
     * @return 返回是否回文链表
     */
    public boolean isPalindrome(ListNode head) {
        long hash1 = 0, hash2 = 0, h = 1;
        long seed = (long) (1e9 + 7);
        ListNode p = head;
        while (p != null) {
            hash1 = hash1 * seed + p.val;
            hash2 = hash2 + h * p.val;
            h *= seed;
            p = p.next;
        }
        return hash1 == hash2;
    }

    public int[] reversePrint(ListNode head) {
        List<Integer> answerList = new ArrayList<>();
        ListNode prev = null, tail = head;
        while(tail != null) {
            ListNode node = tail;
            tail = tail.next;
            node.next = prev;
            prev = node;
            answerList.add(prev.val);
        }
        Collections.reverse(answerList);
        int[] answer = new int[answerList.size()];
        for (int i=0; i<answer.length; ++i) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    /**
     * 反转链表:
     * 递归法完成反转链表
     * @param head 头指针
     * @return 返回反序之后的链表
     * 官方题解：
     *     public ListNode reverseList(ListNode head) {
     *         if (head == null || head.next == null) {
     *             return head;
     *         }
     *         ListNode p = reverseList(head.next);
     *         head.next.next = head;
     *         head.next = null;
     *         return p;
     *     }
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        //得到尾节点
        ListNode tail = head;
        while(tail.next!=null) {
            tail = tail.next;
        }
        helpReverse(head);
        //防止成环
        head.next = null;
        return tail;
    }

    private ListNode helpReverse(ListNode head) {
        //如果子节点为空，直接将head节点返回作为反序之后的父节点
        if (head.next != null) {
            //得到反序之后的子链表
            ListNode node = helpReverse(head.next);
            //将反序之后的节点的子节点设置为当前节点
            node.next = head;
        }
        return head;
    }

    /**
     * 反转链表迭代法：
     * 官方题解：
     *    public ListNode reverseList(ListNode head) {
     *         ListNode prev = null;
     *         ListNode curr = head;
     *         while (curr != null) {
     *             ListNode nextTemp = curr.next;
     *             curr.next = prev;
     *             prev = curr;
     *             curr = nextTemp;
     *         }
     *         return prev;
     *     }
     */
    public ListNode reverseListIteration(ListNode head) {
        if (head == null || head.next==null) return head;
        ListNode tail, itList, node;
        tail = head;
        itList = head.next;
        //防止成环
        head.next = null;

        while(itList!=null) {
            node = itList;
            itList = itList.next;
            node.next = tail;
            tail = node;
        }
        return tail;
    }


    /**
     * 重排序链表：
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * @param head 头节点指针
     */
    public void reorderList(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        for (int i=0; head != null; head=head.next, ++i) {
            map.put(i, head);
        }

        head = map.get(0);
        for (int i= map.size()-1; i> map.size()/2; --i) {
            ListNode node = head.next;
            ListNode last = map.get(i-1);
            head.next = last.next;
            head.next.next = node;
            head = node;
            last.next = null;
        }
    }

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
