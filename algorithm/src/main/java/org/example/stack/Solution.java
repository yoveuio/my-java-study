package org.example.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/30 10:16
 * @Version 1.0
 */
public class Solution {

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
