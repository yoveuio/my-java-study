package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    StringBuilder sb = new StringBuilder();
    Map<Character, Integer> map = new HashMap<>();
    ArrayList<String> answer = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        if (str.length() == 0) return answer;
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        dfs(0, str.length());
        return answer;
    }

    private void dfs(int length, int size) {
        if (length == size) {
            answer.add(sb.toString());
        }
        Set<Character> set = map.keySet();
        for (char c : set) {
            int count = map.get(c);
            if (count <= 0) continue;
            map.replace(c, count - 1);
            sb.append(c);
            dfs(length + 1, size);
            sb.deleteCharAt(length - 1);
            map.put(c, count);
        }
    }
}
