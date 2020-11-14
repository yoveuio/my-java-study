package org.example.array;

import java.util.Arrays;

/**
 * @ClassName ArrayTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/26 8:35
 * @Version 1.0
 */
public class ArrayTest {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.sortArray(new int[]{5, 2, 3, 1});
        System.out.println(Arrays.toString(ints));
    }
}
