package org.example;

import org.example.leetcode.ListNode;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 字节高频题
 * @date 2021/1/9 9:20
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode node = curr.next;
            curr.next = prev;
            prev = curr;
            curr = node;
        }

        return prev;
    }

    /**
     * 最大的第k个元素
     */
    public int findKthLargest(int[] nums, int k) {
        return sort(nums, 0, nums.length - 1, k - 1);
    }

    private int sort(int[] nums, int lo, int hi, int k) {
        int seg = partition(nums, lo, hi);
        if (seg == k) {
            return nums[seg];
        }
        return seg > k ? sort(nums, lo, seg - 1, k) : sort(nums, seg + 1, hi, k);
    }

    private int partition(int[] nums, int lo, int hi) {
        int lt = lo, gt = hi + 1;
        int v = nums[lo];
        while (true) {
            while (++lt <= hi && nums[lt] >= v);
            while (--gt >= lo && nums[gt] < v);
            if (lt >= gt) break;
            int tmp = nums[lt];
            nums[lt] = nums[gt];
            nums[gt] = tmp;
        }
        nums[lo] = nums[gt];
        nums[gt] = v;
        return gt;
    }

    /**
     * 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        byte[] bitmap = new byte[128 / 8 + 1];
        int index = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            int a = c / 8, b = c % 8;
            while ((bitmap[a] & (1 << b)) != 0) {
                int c2 = s.charAt(index++);
                bitmap[c2 / 8] &= ~(1 << (c2 % 8));
            }
            bitmap[a] |= 1 << b;
            ans = Math.max(ans, i - index + 1);
        }
        return ans;
    }

    /**
     * k个一组翻转节点
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        return null;
    }

    private ListNode reverseList(ListNode prev, ListNode curr) {
        ListNode node = prev.next;
        while (node != curr) {
            ListNode tmp = node.next;
            node.next = prev;
            prev = node;
            node = tmp;
        }
        return prev;
    }
}
