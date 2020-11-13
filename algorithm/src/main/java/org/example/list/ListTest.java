package org.example.list;

import org.example.leetcode.ListNode;

/**
 * @ClassName ListTest
 * @Description 测试类
 * @Author yoveuio
 * @Date 2020/11/11 20:24
 * @Version 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode nodeA = new ListNode(2);
        ListNode nodeB = new ListNode(5);
        nodeA.next = new ListNode(4);
        nodeA.next.next = new ListNode(3);
        nodeB.next = new ListNode(6);
        nodeB.next.next = new ListNode(4);
        //solution.addTwoNumbers(nodeA, nodeB);
    }
}
