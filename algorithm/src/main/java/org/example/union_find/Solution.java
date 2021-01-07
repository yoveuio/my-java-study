package org.example.union_find;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 并查集
 * @date 2021/1/7 17:07
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[20];
        solution.init(a, 20);
        a[15] = a[2];
        a[2] = a[0];
        System.out.println(solution.find(a, 15));
        System.out.println(a[15]);
    }

    public void init(int[] parent, int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int[] parent, int i) {
        return parent[i] == i ? parent[i] : (parent[i] = find(parent, parent[i]));
    }

    public boolean union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        if (x != y) {
            parent[x] = parent[y];
            return true;
        }
        return false;
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        int[] parent = new int[n];
        init(parent, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }
}
