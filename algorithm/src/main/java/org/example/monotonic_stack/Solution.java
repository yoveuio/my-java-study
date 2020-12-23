package org.example.monotonic_stack;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 单调栈题目
 * @date 2020/12/17 17:07
 */
public class Solution {
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            num[ch - 'a']--;
            if (vis[ch - 'a']) continue;
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch &&
                    num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            vis[ch - 'a'] = true;
            sb.append(ch);
        }
        return sb.toString();
    }
}
