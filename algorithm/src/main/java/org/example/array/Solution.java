package org.example.array;

import org.example.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Solution
 * @Description leetcode算法章节的题目
 * @Author yoveuio
 * @Date 2020/10/14 18:56
 * @Version 1.0
 */
public class Solution {

    public List<String> commonChars(String[] A) {
        int[][] letter = new int[A.length][26];
        List<String> list = new ArrayList<>();

        for (int i=0; i<A.length; ++i){
            for (char a: A[i].toCharArray()) {
                letter[i][Character.toLowerCase(a)-'a']++;
            }
        }

        for (int i=0; i<26; ++i){
            int min = 0x3f3f3f3f;
            for (int[] ints : letter) {
                min = Math.min(ints[i], min);
                if (min < 0) break;
            }
            for (int j=0; j<min; ++j){
                char a = (char) ('a'+i);
               list.add(String.valueOf(a));
            }
        }
        return list;
    }
}
