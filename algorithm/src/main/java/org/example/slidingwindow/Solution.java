package org.example.slidingwindow;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/17 10:55
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }

    /**
     * LC76. 最小覆盖子串（滑动窗口）
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
     * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * @param s 字符串
     * @param t 匹配的字符串
     * @return 返回最短的子串
     *
     */
    public String minWindow(String s, String t) {
        int[] chS = new int[52];
        int[] chT = new int[52];
        int lengthS = 0, lengthT = 0;
        String answer = s + 'z';
        int head, tail;
        for (char c: t.toCharArray()) {
            chT[getIndex(c)]++;
            lengthT++;
        }

        for (head = 0, tail = 0; head < tail || head == 0;) {
            boolean flag = isSContainT(chS, chT, lengthT);
            if (!flag && tail < s.length()) {
                chS[getIndex(s.charAt(tail++))]++;
                lengthS++;
                continue;
            }
            if (flag){
                answer = answer.length() > lengthS ? s.substring(head, tail) : answer;
            }
            chS[getIndex(s.charAt(head++))]--;
            lengthS--;
        }
        return answer.equals(s+'z') ? "" : answer;
    }

    public int getIndex(char c) {
        return c > 'Z' ? c - 'a' + 26 : c - 'A';
    }

    public boolean isSContainT(int[] s, int[] t, int count) {
        for (int i = 0; i < 52; i++) {
            if (s[i] >= t[i]) {
                count -= t[i];
                if (count <= 0) return true;
            }
        }
        return false;
    }

    /**
     * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
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
}
