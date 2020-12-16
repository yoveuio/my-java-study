package org.example.math;

import java.util.Arrays;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 数学题
 * @date 2020/12/12 17:00
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.monotoneIncreasingDigits(10);
    }

    /**
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * @param nums 非负整数组
     * @return 拼接的数
     */
    public String minNumber(int[] nums) {
        String[] numsS = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsS[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numsS, (o1, o2) -> (o1+o2).compareTo(o2+o1));


        StringBuilder sb = new StringBuilder();

        for (String s: numsS) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 给定一个非负整数N，找出小于或等于N的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     *
     * （当且仅当每个相邻位数上的数字x和y满足x <= y时，我们称这个整数是单调递增的。）
     * 从右到左扫描一遍如果发现右边比左边小，就将右边的全变为9，左边的减一
     */
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            //借位
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }

    /**
     * 求x的n次幂
     */
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        // b小于0的情况
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            // n = f(n) = 1*b1 + 2*b2 + …… + 2^(m-1)*bm
            if((b & 1) == 1) res *= x;
            // x = x ^ 2, x = [1, 2, 4, 8, 16, ...]; 即f(n)中的第n项
            x *= x;
            b >>= 1;
        }
        return res;
    }

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
