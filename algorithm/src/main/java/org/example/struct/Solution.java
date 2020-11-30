package org.example.struct;

import java.util.*;

/**
 * @ClassName Solution
 * @Description offer中的数据结构章节
 * @Author yoveuio
 * @Date 2020/11/28 11:57
 * @Version 1.0
 */
public class Solution {
    /**
     * lru design
     * 设计LRU(最不经常使用算法)缓存结构
     * @param operators operators[opt][key][value]; 当opt=1，表示set操作，当opt=2表示get操作
     * @param k 表示缓存大小
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        List<Integer> answer = new ArrayList<>();
        LRUCache cache = new LRUCache(k);
        for (int[] opt: operators) {
            //set操作
            if (opt[0] == 1) {
                cache.set(opt[1], opt[2]);
            }
            else if (opt[0] == 2) {
                answer.add(cache.get(opt[1]));
            }
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    class LRUCache{
        Map<Integer, Node> map = new HashMap<>();
        Node head = new Node(-1, -1);
        Node tail = new Node(-1, -1);
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public void set(int key, int value) {
            Node node = map.getOrDefault(key, new Node(key, value));
            if (get(key) != -1) {
                node.value = value;
                return;
            }

            if (map.size() >= capacity) {
                Node deleted = head.next;
                map.remove(deleted.key);
                head.next = deleted.next;
                deleted.next.prev = head;
            }
            toTail(node);
            map.put(key, node);
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            toTail(node);
            return node.value;
        }

        private void toTail(Node node) {
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
        }
    }

    class Node{
        Node next;
        Node prev;
        int value;
        int key;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}
