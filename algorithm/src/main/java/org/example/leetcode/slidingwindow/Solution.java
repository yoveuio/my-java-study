package org.example.leetcode.slidingwindow;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/17 10:55
 * @Version 1.0
 */
public class Solution {

    /**
     * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     *
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
     *
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
     *
     * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards
     */
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0, answer = 0;
        int i, j;

        if (cardPoints.length <= k) {
            for (int cardPoint : cardPoints) {
                sum += cardPoint;
            }
            return sum;
        }

        for (i=0; i<k; ++i) {
            sum += cardPoints[i];
        }

        answer = sum;

        for (--i, j=cardPoints.length-1; i>=0; --i, --j) {
            sum = sum - cardPoints[i] + cardPoints[j];
            answer = Math.max(sum, answer);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maxScore(new int[]{96, 90, 41, 82, 39, 74, 64, 50, 30
        }, 8);
        System.out.println(i);
    }

}
