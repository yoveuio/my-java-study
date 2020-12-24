package org.example.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yoveuio
 * @version 1.0
 * @className MaxQueue
 * @description
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value需要返回 -1
 *
 *  双端队列记录递增的最大值
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * @date 2020/12/24 11:20
 */
class MaxQueue {

    Deque<Node> deque;
    Node head, tail;

    public MaxQueue() {
        deque = new ArrayDeque<>();
    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst().value;
    }

    public void push_back(int value) {
        while (!deque.isEmpty() && value > deque.getLast().value) deque.pollLast();
        Node node = new Node(value);
        deque.addLast(node);
        if (head == null) {
            head = tail = node;
        }
        else {
            tail.next = node;
            tail = node;
        }
    }

    public int pop_front() {
        if (head == null) return -1;
        Node node = head;
        head = head.next;
        if (deque.getFirst().equals(node)) {
            deque.pollFirst();
        }
        return node.value;
    }

    class Node{
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
