package org.example.dynamic_programming;

/**
 * @ClassName Main
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/9 19:35
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.lengthOfLIS(new int[]{4,10,4,3,8,9});
        System.out.println(i);
    }
}
