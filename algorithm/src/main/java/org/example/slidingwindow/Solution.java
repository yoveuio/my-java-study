package org.example.slidingwindow;

import java.util.*;

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
        int[] ints = solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(ints);
    }

    /**
     * LC3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (set.contains(c)) {
                set.remove(s.charAt(index++));
            }
            set.add(c);
            ans = Math.max(ans, i - index + 1);
        }
        return ans;
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     * @param nums 数组
     * @param k 目标值
     * @return 返回每个滑动窗口的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) return new int[]{};
        int n = nums.length;
        int[] answer = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (i < k) {
                putMaxToRangeQueue(deque, nums[i]);
                continue;
            }
            answer[prev] = deque.getFirst();
            if (deque.getFirst() == nums[prev++]) {
                deque.pollFirst();
            }
            putMaxToRangeQueue(deque, nums[i]);
        }
        answer[n - k] = deque.getFirst();
        return answer;
    }

    private <T extends Comparable<T>>void putMaxToRangeQueue(Deque<T> deque, T num){
        while (!deque.isEmpty() && deque.getLast().compareTo(num) < 0) {
            deque.pollLast();
        }
        deque.offerLast(num);
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
