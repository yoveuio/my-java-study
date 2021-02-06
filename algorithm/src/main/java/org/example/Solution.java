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

    public int[] multiply(int[] A) {
        int n = A.length;
        if (A.length <= 1) return null;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] answer = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * A[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * A[i + 1];
        }
        for (int i = 0; i < n; i++) {
            answer[i] = left[i] * right[i];
        }
        return answer;
    }

    public boolean duplicate(int numbers[],int length,int [] duplication) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            while (numbers[i] != i) {
                int tmp = numbers[numbers[i]];
                if (tmp == numbers[i]) {
                    duplication[0] = i;
                    return true;
                }
                numbers[numbers[i]] = numbers[i];
                numbers[i] = tmp;
            }
        }
        return false;
    }

    public int Add(int num1,int num2) {
        while (num2 != 0) {
            int c = (num1 & num2) << 1;
            num1 ^= num2;
            num2 = c;
        }
        return num1;
    }

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
