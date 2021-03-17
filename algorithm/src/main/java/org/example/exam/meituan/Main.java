package org.example.exam.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yoveuio
 * @version 1.0
 * @className Main
 * @description TODO
 * @date 2021/3/1 20:49
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += Math.abs(a[i] - i);
        }
        System.out.println(ans);
    }
}
