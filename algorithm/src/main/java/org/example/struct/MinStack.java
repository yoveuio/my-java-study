package org.example.struct;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className MinStack
 * @description TODO
 * @date 2021/1/11 10:15
 */
public class MinStack {

    private Deque<Integer> stack;
    private Deque<Integer> mines;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
        mines = new ArrayDeque<>();
    }

    public void push(int x) {
        if (mines.isEmpty() || mines.peekLast() >= x) mines.addLast(x);
        stack.addLast(x);
    }

    public void pop() {
        if (Objects.equals(stack.getLast(), mines.getLast())) mines.pollLast();
        stack.pollLast();
    }

    public int top() {
        return stack.getLast();
    }

    public int getMin() {
        if (mines.isEmpty()) throw new RuntimeException();
        return mines.peekLast();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(85);
        minStack.push(-99);
        minStack.push(63);
    }
}
