package org.example.union_find;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 并查集
 * @date 2021/1/7 17:07
 */
@SuppressWarnings("unused")
public class Solution {

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 41; i++) {
            nodes.add(new Node(i));
        }

        UnionFind<Node> unionFind = new UnionFind<>(nodes);
        for (int i = 1; i < 41; i++) {
            unionFind.union(nodes.get(i), nodes.get(i - 1));
        }
        System.out.println(unionFind.isUnion(nodes.get(1), new Node(2)));
    }

    public static class Node {
        int a;

        public Node(int a) {this.a = a;}

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;
            Node o = (Node)obj;
            return this.a == o.a;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + a;
            return result;
        }
    }

    public static class UnionFind<T>{
        private final HashMap<T, T> fatherMap;
        private final HashMap<T, Integer> sizeMap;


        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public UnionFind (Collection<T> collection) {
            this();
            for (T node : collection) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private T find(T node) {
            T father = fatherMap.get(node);
            if (father == node) return father;
            father = find(father);
            fatherMap.put(node, father);
            return father;
        }

        public boolean isUnion(T aNode, T bNode) {
            return find(aNode) == find(bNode);
        }

        public void union(T aNode, T bNode) {
            if (aNode == null || bNode == null) return;
            T aHead = find(aNode);
            T bHead = find(bNode);

            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                if (aSize <= bSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSize + bSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSize + bSize);
                }
            }
        }
    }
}
