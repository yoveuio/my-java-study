package org.example.stack;

import java.util.*;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/30 10:16
 * @Version 1.0
 */
public class Solution {

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        stack.push(nums[nums.length - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < max) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                max = stack.pop();
            }
            if (nums[i] > max) {
                stack.push(nums[i]);
            }
        }
        return false;
    }

    /**
     *
     * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
     * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
     * @param s 一个包含很多括号的字符串
     * @return 返回字符串是否合法
     */
    public boolean isValid (String s) {
        if(s == null) return false;
        // write code here
        Map<Character, Character> charMap = new HashMap<Character, Character>(){{
            put(']', '[');
            put('}', '{');
            put(')', '(');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (char c: s.toCharArray()) {
            if (!charMap.containsKey(c)) {
                stack.push(c);
            }
            else {
                char result = charMap.get(c);
                if (stack.isEmpty()|| result != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
