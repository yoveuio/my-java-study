package org.example;

import java.util.Scanner;

public class Main {
    static final int MOD = (int)1e9 + 7;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            if ((a[i] & 1) != 0) dp[i] = dp[i - 1] + 1;
            else dp[i] = dp[i - 1];
        }
        int[] ans = new int[m];
        int index = 0;
        while (m-- > 0) {
            int type = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            int count = dp[r] - dp[l - 1];
            int x = pow(2, count) - 1;
            int y = pow(2, r - l + 1) - x - 1;

            if (type == 1) {
                ans[index++] = x;
            } else {
                ans[index++] = y;
            }
        }
        for (int i: ans) {
            System.out.println(i);
        }
    }

    static int pow(int x, int y) {
        int ans = 1;
        while (y != 0) {
            if ((y & 1) != 0) {
                ans = (int) ((long) ans * x % MOD);
            }
            x *= x;
            y >>= 1;
        }
        return ans;
    }
}
