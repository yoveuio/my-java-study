package org.example.string;



import java.util.*;

/**
 * @ClassName Solution
 * @Description 字符串有关算法
 * @Author yoveuio
 * @Date 2020/10/21 19:03
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.permutation("abc");
    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 计数排序
     * @param strs 一个字符串数组，里面有0~n个字母异位词
     * @return 返回字母异位词分组后的结果
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> answers = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) {
            int[] counts = new int[26];
            for (char ch: s.toCharArray()) {
                counts[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char)(i + 'a'));
                    sb.append(counts[i]);
                }
            }
            List<String> answer = map.getOrDefault(sb.toString(), new ArrayList<>());
            answer.add(s);
            map.put(sb.toString(), answer);
        }

        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            answers.add(entry.getValue());
        }
        return answers;
    }

    /**
     * 找到第一个只出现一次的数
     * @param s 一个包含很多字符的数
     * @return 第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        Map<Character, Integer> answer = new HashMap<>();
        for (char c: s.toCharArray()) {
            answer.put(c, answer.getOrDefault(c, 0) + 1);
        }

        for (char c: s.toCharArray()) {
            if (answer.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * 打印出字符串中字符所有的排列，不能有重复元素
     * @param s 给定字符串
     * @return 任意顺序的一个排列
     */
    List<String> answer = new LinkedList<>();
    HashMap<Character, Integer> mapS = new HashMap<>();
    public String[] permutation(String s) {
        for (char c: s.toCharArray()) {
            mapS.put(c, mapS.getOrDefault(c, 0) + 1);
        }
        permutationHandler("", s.length());
        return answer.toArray(new String[answer.size()]);
    }

    private void permutationHandler(String s, int size) {
        if (s.length() == size) {
            answer.add(s);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(s);
        Set<Character> characters = mapS.keySet();
        for (char c: characters) {
            int count = mapS.get(c);
            if (count <= 0) continue;
            mapS.put(c, count-1);
            stringBuilder.append(c);
            permutationHandler(stringBuilder.toString(), size);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            mapS.put(c, count);
        }
    }

    /**
     * 给定字符串s、t，判断t是否是s的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        int[] letterS = new int[26];
        int[] letterT = new int[26];
        final int a = 'a';
        for (int i = 0; i < s.length(); i++) {
            letterS[s.charAt(i) - a]++;
            letterT[t.charAt(i) - a]++;
        }

        for (int i=0; i<26; i++) {
            if (letterS[i] != letterT[i]) return false;
        }
        return true;
    }

    /**
     * 最长不含重复元素的子串
     */
    public int lengthOfLongestSubstring(String s) {
        return -1;
    }

    /**
     * 将一个字符串尽可能的分割，保证每个字母只会出现在一个片段中
     * 解法：贪心+双指针
     * 记录开始位置和结束位置。保证片段中的每一对start-end都在范围内即可
     * @param S 被分割的字符串
     * @return 返回分割后的下标
     */
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    /**
     * 把字符串s中的每个空格替换成"%20"
     * @param s 字符串
     * @return 处理后的字符串
     */
    public String replaceSpace(String s) {
        StringBuilder stringBuffer = new StringBuilder();
        for (char c: s.toCharArray()) {
            stringBuffer.append(c==' ' ? "%20": c);
        }
        return stringBuffer.toString();
    }

    /**
     * 名字匹配，使用双指针
     * @param name 名字
     * @param typed 匹配的名字
     * @return 匹配成功返回true
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
