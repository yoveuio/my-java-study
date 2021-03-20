package org.example;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className Save
 * @description 备份
 * @date 2021/3/13 15:53
 */
public class Save {

    public static void v0(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int j = 0;
        int[] dp = new int[n];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            if (i < m - 1) continue;
            dp[i] = getK(map);
            if (map.get(a[j]) == 1) map.remove(a[j]);
            else map.put(a[j], map.get(a[j]) - 1);
            j++;
        }

        System.out.println(m - 1);
        for (int i = m - 1; i < n; i++) {
            System.out.println(dp[i]);
        }
    }



    static int getK0(Map<Integer, Integer> map) {
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int max = 0;
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry: entries) {
            if (entry.getValue() > max || (entry.getValue() == max &&
                    entry.getKey() < ans)) {
                ans = entry.getKey();
                max = entry.getValue();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] dp = new int[n];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (i < n - m) continue;
            System.out.println(i);
            dp[i] = getK(map);
        }
        for (int i = n - m; i < n; i++) {
            System.out.println(dp[i]);
        }
    }

    static int getK(Map<Integer, Integer> map) {
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int max = 0;
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry: entries) {
            if (entry.getValue() > max || (entry.getValue() == max && ans >= max)) {
                ans = entry.getKey();
                max = entry.getValue();
            }
        }
        return ans;
    }

    void x1(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void a2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();

        List<String> answers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            if (!isNumber(s.charAt(i))) {
                if (!flag) continue;
                int index = 0;
                while (index < sb.length() && sb.charAt(index) == '0') index++;
                String answer = sb.substring(index);
                if (answer.length() == 0) answers.add("0");
                else answers.add(answer);
                sb = new StringBuilder();
                flag = false;
            } else {
                flag = true;
                sb.append(s.charAt(i));
            }
        }
        if (flag) {
            int index = 0;
            while (index < sb.length() && sb.charAt(index) == '0') index++;
            String answer = sb.substring(index);
            if (answer.length() == 0) answers.add("0");
            else answers.add(answer);
        }

        answers.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }
                for (int i = 0; i < o2.length(); i++) {
                    if (o1.charAt(i) == o2.charAt(i)) continue;
                    return o1.charAt(i) - o2.charAt(i);
                }
                return 0;
            }
        });
        for (String i : answers) {
            System.out.println(i);
        }
    }

    private static boolean isNumber(char i) {
        return i >= '0' && i <= '9';
    }
}
