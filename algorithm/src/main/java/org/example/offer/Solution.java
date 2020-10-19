package org.example.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/17 14:58
 * @Version 1.0
 */
public class Solution {

    /**
     * 找到数组中的第一个重复数字
     * 进阶考点：原地Hash
     * @param nums 给定的一个数组
     * @return 任意一个重复数字
     */
    public int findRepeatNumber(int[] nums) {
/*        Set<Integer> set = new HashSet<>();

        for (int i: nums) {
            if (!set.contains(i)) {
                set.add(i);
            }
            else return i;
        }
        throw new RuntimeException();*/
        int temp;
        for (int i=0; i<nums.length; ++i) {
            while(nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }


    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }
}
