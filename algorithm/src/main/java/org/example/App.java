package org.example;

import java.util.HashMap;

/**
 * Hello world!
 */
public class App {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回满足条件的最大的x。
     *
     * @param a int整型 代表题意中的a
     * @param b int整型 代表题意中的b
     * @param n int整型 代表题意中的n
     * @return int整型
     */
    public int solve(int a, int b, int n) {
        // write code here
        for(int i=n; i>=0; i--) {
            if (i % a  == b) {
                return i;
            }
        }
        return -1;
    }

    public int string2 (int k, String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length();) {
            char v = s.charAt(i);
            while (++i < s.length() && s.charAt(i) == v);
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        int max = 0;
        for (char i='a'; i<='z'; i++) {
            Integer integer = map.getOrDefault(i, 0);

            max = Math.max(max, integer);
        }
        return Math.min((max + k), s.length());
    }
}
