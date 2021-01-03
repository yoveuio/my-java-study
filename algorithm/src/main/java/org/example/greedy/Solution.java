package org.example.greedy;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 贪心
 * @date 2020/12/24 10:20
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.minPatches(new int[]{1, 3}, 6);
        System.out.println(i);
    }
    /* ------------------------------------------------------------------------------------------ */

    /**
     * LC330. 按要求补齐数组
     * 给定一个已排序的正整数数组 nums，和一个正整数n。
     * 从[1, n]区间内选取任意个数字补充到nums中，使得[1, n]区间内的任何数字都可以用nums中某几个数字的和来表示。
     * 请输出满足上述要求的最少需要补充的数字个数。
     *
     * 基本思路：
     *      对于x，如果[0, x-1]中的数组全部被覆盖，且x也在数组中，那么[0, 2x - 1]也全部被覆盖
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/patching-array
     * @param nums 已排序的正整数数组
     * @param n 正整数
     * @return 返回最少补充的数字个数
     */
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }


    public int myCandy(int[] ratings) {
        int n = ratings.length;
        int inc = 1, dec = 0, rec = 1, top = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                top = ratings[i] == ratings[i - 1] ? 1 : top + 1;
                rec += top;
                inc = top;
            }
            else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                rec += dec;
                top = 1;
            }
        }
        return rec;
    }

    /**
     * LC135. 分发糖果
     * 老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     *      - 每个孩子至少分配到 1 个糖果。
     *      - 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/candy
     *
     * //一次遍历使用o(n)时间复杂度和常数空间
     * class Solution {
     *     public int candy(int[] ratings) {
     *         int n = ratings.length;
     *         // ret记录答案，inc记录上升的高度，dec记录应该下降的高度，pre指向峰顶
     *         int ret = 1, inc = 1, dec = 0, pre = 1;
     *         for (int i = 1; i < n; i++) {
     *             // 递增序列
     *             if (ratings[i] >= ratings[i - 1]) {
     *                 // 下降数置0
     *                 dec = 0;
     *                 // 根据贪心，如果出现评分相同的情况把后面那个置1
     *                 pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
     *                 // 加上当前孩子需要分配的糖果
     *                 ret += pre;
     *                 inc = pre;
     *             } else {
     *                 dec++;
     *                 // 峰顶升高
     *                 if (dec == inc) {
     *                     dec++;
     *                 }
     *                 ret += dec;
     *                 pre = 1;
     *             }
     *         }
     *         return ret;
     *     }
     * }
     * @param ratings 孩子的评分
     * @return 返回最少的糖果总数
     */
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length < 1) return 0;
        int n = ratings.length;
        int[] left = new int[n];
        int ans = 0, right = 1;

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
            else {
                left[i] = 1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            }
            else {
                right = 1;
            }
            ans += Math.max(right, left[i]);
        }
        return ans;
    }
}
