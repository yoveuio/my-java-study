package org.example.doublepointer;

import org.example.leetcode.ListNode;

import java.util.Arrays;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/19 9:11
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {

    }

    public void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * public void moveZeroes(int[] nums) { 
     *     int n = nums.length, left = 0, right = 0;
     *     while (right < n) {
     *         if (nums[right] != 0) {
     *             swap(nums, left, right);
     *             left++;
     *         }
     *         right++;
     *     }
     * }
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zero = 0, notZero = 0;
        int len = nums.length;
        while (true) {
            while (nums[notZero] != 0 && notZero <= len-1) notZero++;
            zero = notZero + 1;
            while (zero <= len-1 && nums[zero] == 0) zero++;
            if (zero == len) break;
            swap(nums, zero, notZero);
        }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode pos = sentinel;
        ListNode point;

        while(pos.next!=null) {
            if (pos.next.val==x) {
                break;
            }
            pos = pos.next;
        }

        if (pos.next==null) return head;
        point = pos.next.next;
        while (point != null) {
            if (point.val < x) {
                ListNode node = point;
                point = point.next;
                node.next = pos.next;
                pos.next = node;
                pos = node;
            }
            else {
                point = point.next;
            }
        }
        return head;
    }

    /**
     * 通过双指针删除排序数组中重复元素
     */
    public int[] remElement(int[] arr, int index) {

        for (int i = index + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }

        return arr;
    }

    public int removeDuplicates(int[] nums) {
        int i = 1, count = 1, length = nums.length;
        while (i < length) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > 2) {
                    this.remElement(nums, i);
                    i--;
                    length--;
                }
            } else {
                count = 1;
            }
            i++;
        }

        return length;
    }

    /**
     * 通过双指针判断是否链表有环：
     *  两个指针同时遍历，如果速度快的指针与速度慢的指针碰撞了，说明有环
     * @param head 链表头节点
     * @return 有环返回false
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        //first的指针以second指针两倍的速度遍历链表，如果发生碰撞说明有环
        ListNode first = head.next;
        ListNode second = head;

        while(first != second) {
            if (first == null || first.next == null) {
                return false;
            }
//            if (first == second || first.next == second) {
//                return true;
//            }
            first = first.next.next;
            second = second.next;
        }
        return true;
    }

    /**
     * 遍历找到有序数组中的三个元素的期望值
     * @param nums 有序数组
     * @param target 期望值
     * @return 最接近期望值的值
     */
    public int threeSumClosest(int[] nums, int target) {
        int answer = 0x3f3f3f3f;
        //三指针
        int a, b, c;

        Arrays.sort(nums);
        for (a = 0; a < nums.length; ++a) {
            int sum = target - nums[a];
            b = a + 1;
            c = nums.length - 1;
            while(b < c) {
                answer = Math.abs(nums[a]+nums[b]+nums[c]-target) < Math.abs(answer-target) ?
                        nums[a]+nums[b]+nums[c] : answer;
                if (nums[b] + nums[c] < sum) {
                    b++;
                }
                else if (nums[b] + nums[c] > sum) {
                    c--;
                }
                else {
                    return target;
                }
            }
        }

        return answer;
    }

    /**
     * 删除链表动态位置的节点
     * @param head 头节点
     * @param n 相对的位置
     * @return 删除后的链表
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node, tail;
        int count = 0;

        node = tail = head;
        while(tail.next != null) {
            if (count != n) {
                tail = tail.next;
                count++;
            } else {
                tail = tail.next;
                node = node.next;
            }
        }

        if (count < n) {
            head = head.next;
        }
        else {
            assert node.next != null;
            node.next = node.next.next;
        }
        return head;
    }

}
