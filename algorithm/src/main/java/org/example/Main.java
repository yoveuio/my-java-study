package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int j = 0;
        int[] dp = new int[n];
        Node[] nodes = new Node[m];
        Map<Integer, Node> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            if (map.containsKey(a[i])) map.put(a[i], new Node(a[i]));
            Node node = map.get(a[i]);
            node.y++;
            if (i < m - 1) continue;
            if (map.get(a[j]).y == 1) map.remove(a[j]);
            else map.get(a[j]).y--;
            j++;
        }

        System.out.println(m - 1);
        for (int i = m - 1; i < n; i++) {
            System.out.println(dp[i]);
        }
    }

    public void getK() {

    }

    static class Node{
        int x, y;
        public Node(int x) {
            this.x = x;
        }
    }


}
