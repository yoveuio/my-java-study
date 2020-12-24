package org.example.greedy;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 贪心
 * @date 2020/12/24 10:20
 */
public class Solution {

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
