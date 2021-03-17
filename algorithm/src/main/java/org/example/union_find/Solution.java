package org.example.union_find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 并查集
 * @date 2021/1/7 17:07
 */
public class Solution {

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        UnionFind unionFind = new UnionFind();
        unionFind.makeUnion(nodes);
        for (int i = 0; i < 41; i++) {
            nodes.add(new Node(i));
        }

        unionFind.makeUnion(nodes);
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

    public static class UnionFind{
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeUnion(List<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();

            for (Node node: nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node find(Node node) {
            Node father = fatherMap.get(node);
            if (father == node) return father;
            father = find(father);
            fatherMap.put(node, father);
            return father;
        }

        public boolean isUnion(Node aNode, Node bNode) {
            return find(aNode) == find(bNode);
        }

        public void union(Node aNode, Node bNode) {
            if (aNode == null || bNode == null) return;
            Node aHead = find(aNode);
            Node bHead = find(bNode);

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
