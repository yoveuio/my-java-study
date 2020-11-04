package org.example.offer;

/**
 * @ClassName Array
 * @Description 剑指Offer!数组相关题目
 * @Author yoveuio
 * @Date 2020/11/3 16:16
 * @Version 1.0
 */
public class Array {

    public int[] exchange(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (l < r && (nums[l] & 1) == 1) {
                l++;
            }
            while (l < r && (nums[r] & 1) == 0) {
                r--;
            }
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
        return nums;
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int[] answer = new int[matrix.length * matrix[0].length];
        int l = 0, t = 0, b = matrix.length - 1, r = matrix[0].length - 1, count = 0;


        while (true) {
            for (int i = l; i <= r; ++i) answer[count++] = matrix[t][i];
            if (++t > b) break;
            for (int i = t; i <= b; ++i) answer[count++] = matrix[i][r];
            if (l > --r) break;
            for (int i = r; i >= l; --i) answer[count++] = matrix[b][i];
            if (t > --b) break;
            for (int i = b; i >= t; --i) answer[count++] = matrix[i][l];
            if (++l > r) break;
        }
        return answer;
    }
}
