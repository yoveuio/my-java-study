package org.example;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }

        UnionFind union = new UnionFind(set);
        for (int num: nums) {
            if (set.contains(num - 1) && !union.isUnion(num, num - 1)) union.union(num, num - 1);
        }
        return union.getMaxLength();
    }

    static class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> size;

        UnionFind(Collection<Integer> collection) {
            parent = new HashMap<>();
            size = new HashMap<>();
            for (int i: collection) {
                parent.put(i, i);
                size.put(i, 1);
            }
        }

        private int find(int i) {
            int father = parent.get(i);
            if (i == father) return father;
            father = find(father);
            parent.put(i, father);
            return father;
        }

        public void union(int i, int j) {
            i = find(i);
            j = find(j);
            if (i != j) {
                int iSize = size.get(i);
                int jSize = size.get(j);
                if (iSize < jSize) {
                    parent.put(i, j);
                    size.put(i, iSize + jSize);
                } else {
                    parent.put(j, i);
                    size.put(j, iSize + jSize);
                }
            }
        }

        public boolean isUnion(int i, int j) {
            return find(i) == find(j);
        }

        public int getMaxLength() {
            Map<Integer, Integer> length = new HashMap<>();
            Set<Integer> keys = parent.keySet();
            int ans = 0;
            for (int key: keys) {
                int father = find(key);
                int fatherLength = length.getOrDefault(father, 0) + 1;
                length.put(father, fatherLength);
                ans = Math.max(ans, fatherLength);
            }
            return ans;
        }
    }
}
