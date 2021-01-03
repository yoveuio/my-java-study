package org.example;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        App app = new App();

    }

    /**
     * LC1046. 最后一块石头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        if (stones.length < 1) return 0;
        Queue<Integer> queue = new PriorityQueue<>((x1, x2)-> x2 - x1);
        for (int stone: stones) {
            queue.offer(stone);
        }

        while (queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();
            if (a != b) queue.offer(a - b);
        }
        return queue.isEmpty() ? 0 : queue.peek();
    }
}
