package org.example.string;


import java.util.Arrays;

/**
 * @ClassName Solution
 * @Description 字符串有关算法
 * @Author yoveuio
 * @Date 2020/10/21 19:03
 * @Version 1.0
 */
public class Solution {

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
