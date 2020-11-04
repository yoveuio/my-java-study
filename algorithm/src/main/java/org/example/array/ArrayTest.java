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
        //solution.smallerNumbersThanCurrent(new int[]{5,0,10,0,10,6});
        int[][] insert = solution.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        System.out.println(Arrays.deepToString(insert));
    }
}
