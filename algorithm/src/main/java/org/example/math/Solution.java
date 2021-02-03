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
     * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
     *
     * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            // 当n=206，十位为小于1，因此不需要计算2开始的1的个数，也就是+0
            // 当n=216，十位为1，单独加上210-216的1的个数，也就是余数b+1
            // 当n=226，十位大于1，可以和前面的0-100，100-200合并讨论
            // +8避免一次判断，当十位为0，1不能与前面的合并讨论
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    /**
     * LC169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于n/2的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     */
    public int majorityElement(int[] nums) {
        int res = 0, max = nums[0];
        for (int num: nums) {
            if (max == num) res++;
            else if (res == 0) {
                max = num;
                res++;
            }
            else {
                res--;
            }
        }
        return max;
    }

    /**
     * 剑指 Offer 65. 不用加减乘除做加法
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     * 状态转移方程： Y = ((a ^ b) & ~res) | (res & ~(a ^ b))
     *              res = (a & b) + (a | b) & res
     *
     *              y = a ^ b
     *              c = (a & b) << 1
     */
    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    /**
     * 剑指 Offer 49. 丑数
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     *
     * @param n 要求的第几个数
     * @return 第n个丑数
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int a = 0, b = 0, c = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n - 1];
    }

    /**
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * @param nums 非负整数组
     * @return 拼接的数
     */
    public String minNumber(int[] nums) {
        String[] numsS = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsS[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numsS, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));


        StringBuilder sb = new StringBuilder();

        for (String s : numsS) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 给定一个非负整数N，找出小于或等于N的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     * <p>
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
        if (x == 0) return 0;
        long b = n;
        double res = 1.0;
        // b小于0的情况
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            // n = f(n) = 1*b1 + 2*b2 + …… + 2^(m-1)*bm
            if ((b & 1) == 1) res *= x;
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
        for (int num : nums) {
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
        for (int num : nums) {
            k ^= num;
        }


        k = k & (-k);
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & k) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}
