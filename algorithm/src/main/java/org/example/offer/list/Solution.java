package org.example.offer.list;

import org.example.leetcode.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/1 21:18
 * @Version 1.0
 */
public class Solution {
    /**
     * 判断栈的压入压出是否满足要求
     * Stack<Integer> stack = new Stack<>();
     * int i = 0;
     * for(int num : pushed) {
     * stack.push(num); // num 入栈
     * while(!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
     * stack.pop();
     * i++;
     * }
     * }
     * return stack.isEmpty();
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num); // num 入栈
            while (!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode sentinel, front, tail;
        tail = sentinel = new ListNode(-1);
        sentinel.next = head;
        front = head;
        while (front != null) {
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
