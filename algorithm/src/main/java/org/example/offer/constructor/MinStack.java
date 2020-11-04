package org.example.offer.constructor;

/**
 * @ClassName MinStack
 * @Description 实现一个能记录最小值的栈
 * @Author yoveuio
 * @Date 2020/11/3 16:45
 * @Version 1.0
 */
public class MinStack {

    private Node head;
    class Node{
        int val;
        Node next;
        int min;

        public Node(int val, Node next, int min) {
            this.val = val;
            this.next = next;
            this.min = min;
        }
    }

    /** initialize your data structure here. */
    public MinStack() {}

    public void push(int x) {
        if (head == null) head = new Node(x, null, x);
        else {
            head = new Node(x, head, Math.min(x, head.val));
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int min() {
        return head.min;
    }
}
