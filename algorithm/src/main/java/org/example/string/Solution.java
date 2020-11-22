package org.example.string;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Solution
 * @Description 字符串有关算法
 * @Author yoveuio
 * @Date 2020/10/21 19:03
 * @Version 1.0
 */
public class Solution {

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
