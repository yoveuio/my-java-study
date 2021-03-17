package org.example.exam.zijie;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int p = sc.nextInt();

            Task[] nodes = new Task[m];
            for (int i = 0; i < m; i++) {
                nodes[i] = new Task(sc.nextInt());
            }
            for (int i = 0; i < m; i++) {
                nodes[i].b = sc.nextInt();
            }
            Arrays.sort(nodes, Comparator.comparingInt(o -> o.a));

            ans = 0;
            for (int i = 0; i < m; i++) {
                ans += dfs(nodes, i, n, p);
            }
            System.out.println(ans);
        }
    }

    final static int MOD = (int) (1e9 + 7);
    static int ans;
    static int dfs(Task[] tasks, int i, int n, int p) {
        if (i >= tasks.length) return 0;
        if (tasks[i].a > n) return 0;
        n -= tasks[i].a;
        p -= tasks[i].b;
        int res = 0;
        if (p <= 0) res = 1;
        for (int k = i + 1; k < tasks.length; k++) {
            if (n < tasks[k].a) break;
            res += dfs(tasks, k, n, p) % MOD;
        }
        return res;
    }

    static class Task{
        int a, b;
        public Task(int a) {
            this.a = a;
        }
    }
}

/*
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int p = sc.nextInt();

            Task[] nodes = new Task[m];
            for (int i = 0; i < m; i++) {
                nodes[i] = new Task(sc.nextInt());
            }
            for (int i = 0; i < m; i++) {
                nodes[i].b = sc.nextInt();
            }

            ans = 0;
            for (int i = 0; i < m; i++) {
                ans += dfs(nodes, i, n, p);
            }
            System.out.println(ans);
        }
    }

    final static int MOD = (int) (1e9 + 7);
    static int ans;
    static int dfs(Task[] tasks, int i, int n, int p) {
        if (i >= tasks.length) return 0;
        if (tasks[i].a > n) return 0;
        n -= tasks[i].a;
        p -= tasks[i].b;
        int res = 0;
        if (p <= 0) res = 1;
        for (int k = i + 1; k < tasks.length; k++) {
            if (n < tasks[k].a) continue;
            res += dfs(tasks, k, n, p) % MOD;
        }
        return res;
    }

    static class Task{
        int a, b;
        public Task(int a) {
            this.a = a;
        }
    }
}
*/

