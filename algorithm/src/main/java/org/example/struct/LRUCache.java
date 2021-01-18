package org.example.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yoveuio
 * @version 1.0
 * @className LRUCache
 * @description TODO
 * @date 2021/1/11 10:13
 */
class LRUCache {
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
    static class Node {
        Node next;
        Node prev;
        int value;
        int key;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

