package org.example;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
public class Solution {

    class LRUCache {

        Map<Integer, Node> map;
        LinkedList<Node> deque;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            deque = new LinkedList<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            updateDeque(node);
            return map.get(key).value;
        }

        public void put(int key, int value) {
            Node node = map.getOrDefault(key, new Node(key, value));
            if (get(key) != -1) {
                node.value = value;
                return;
            }

            if (deque.size() >= capacity) {
                Node first = deque.removeFirst();
                map.remove(first.key);
            }
            deque.addLast(node);
            map.put(key, node);
        }

        void updateDeque(Node node) {
            deque.remove(node);
            deque.addLast(node);
        }

        class Node {
            int key;
            int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> answers = new ArrayList<>();
        StringBuilder answer = new StringBuilder();
        int prev = 0, curr = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || nums[i] != nums[curr] + 1) {
                if (prev != curr) answer.append(nums[prev]).append("->").append(nums[curr]);
                else answer.append(nums[prev]);
                answers.add(answer.toString());
                answer.delete(0, answer.length());
                prev = i;
            }
            curr++;
        }
        return answers;
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int sell1 = 0, buy1 = -prices[0];
        int sell2 = 0, buy2 = -prices[0];

        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }

        return sell2;
    }
}
