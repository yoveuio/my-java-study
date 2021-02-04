package org.example;

import org.example.leetcode.ListNode;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode point1 = pHead1;
        ListNode point2 = pHead2;

        while (point1 != point2) {
            point1 = point1 == null ? pHead2 : point1.next;
            point2 = point2 == null ? pHead1 : point2.next;
        }
        return point1;
    }

    public int GetNumberOfK(int [] array , int k) {
        return getMinIndex(array, k + 1) - getMinIndex(array, k);
    }

    public int getMinIndex(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int mid ;

        while (l <= r) {
            mid = (r - l >> 1) + l;
            if (nums[mid] <= k) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return r;
    }
}
