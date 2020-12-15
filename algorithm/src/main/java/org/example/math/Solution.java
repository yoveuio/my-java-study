package org.example.math;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 数学题
 * @date 2020/12/12 17:00
 */
public class Solution {

    /**
     * 在一个数组nums中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    /**
     * 剑指56 数组中数字出现的个数
     * 知识点：异或，不同数分组，k&(-k)得第一个最低的为1位
     */
    public int[] singleNumbers(int[] nums) {
        int k = 0;
        for (int num: nums) {
            k ^= num;
        }


        k = k & (-k);
        int a = 0, b = 0;
        for (int num: nums) {
            if ((num & k) == 0) {
                a ^= num;
            }
            else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}
