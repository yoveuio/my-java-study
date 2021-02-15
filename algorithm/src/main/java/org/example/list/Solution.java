package org.example.list;

import org.example.leetcode.ListNode;
import org.example.leetcode.Node;

import java.util.*;

/**
 * @ClassName Solution
 * @Description 有关列表的算法
 * @Author yoveuio
 * @Date 2020/10/18 9:36
 * @Version 1.0
 */
public class Solution {

    /**
     * 23. 合并K个升序链表
     *
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 优先队列，归并
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge (ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;
        int mid = (r - l >> 1) + l;
        return mergeTwoList(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(-1);
        ListNode curr = sentinel;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;
        return sentinel.next;
    }

    /**
     * LC160. 相交链表
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     * 走你来时的路
     * 关键是要遍历到链表的最后一个节点后面的null，只有这样当两个链表不相交的时候有共同的值null，才能走出循环
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pointA = headA;
        ListNode pointB = headB;

        while (pointA != pointB) {
            pointA = pointA == null ? headB : pointA.next;
            pointB = pointB == null ? headA : pointB.next;
        }
        return pointA;
    }

    /**
     * LC86. 分隔链表
     * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-list
     */
    public ListNode partition(ListNode head, int x) {
        ListNode sentinelA = new ListNode(-1);
        ListNode sentinelB = new ListNode(-1);
        ListNode pointA = sentinelA, pointB = sentinelB;
        while (head != null) {
            if (head.val < x) {
                pointA.next = head;
                pointA = pointA.next;
            }
            else {
                pointB.next = head;
                pointB = pointB.next;
            }
            head = head.next;
        }
        pointA.next = sentinelB.next;
        pointB.next = null;
        return sentinelA.next;
    }

    /**
     * 链表中的倒数第k个节点
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode curr = head;
        for (int i=0; i<k; i++) {
            if (curr==null) return sentinel.next;
            curr = curr.next;
        }

        while (curr != null) {
            curr = curr.next;
            prev = prev.next;
        }
        return prev.next;
    }

    /**
     * 复杂链表的深拷贝
     * 先复制每个节点，通过辅助线容易找到被复制对象的节点
     */
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while(cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while(cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }

    /**
     * 快慢指针实现链表的归并排序
     *
     */
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    /**
     * 奇偶链表，将一个链表的所有的奇节点排序在一起，偶节点排序在一起
     * 题解：
     * public ListNode oddEvenList(ListNode head) {
     *     if (head == null) {
     *         return head;
     *     }
     *     ListNode evenHead = head.next;
     *     ListNode odd = head, even = evenHead;
     *     while (even != null && even.next != null) {
     *         odd.next = even.next;
     *         odd = odd.next;
     *         even.next = odd.next;
     *         even = even.next;
     *     }
     *     odd.next = evenHead;
     *     return head;
     * }
     * https://leetcode-cn.com/problems/odd-even-linked-list/
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd, sentinelOdd;
        ListNode even, sentinelEven;
        boolean flag = false;

        sentinelOdd = new ListNode(-1);
        sentinelEven = new ListNode(-1);
        odd = sentinelOdd;
        even = sentinelEven;
        while (head != null) {
            if (!flag) {
                odd.next = head;
                head = head.next;
                odd = odd.next;
            }
            else {
                even.next = head;
                head = head.next;
                even = even.next;
            }
            flag = !flag;
        }
        odd.next = sentinelEven.next;
        even.next = null;

        return sentinelOdd.next;
    }

    /**
     * 两数相加，
     * 列表数字逆序存储，返回相加的结果
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 通过hash公式
     * hash = a[1]*seed^0 + ... + a[n]*seed^n-1
     * 由于是回文数组，逆序链表的hash应该是相同的
     * @param head 链表头节点
     * @return 返回是否回文链表
     */
    public boolean isPalindrome(ListNode head) {
        long hash1 = 0, hash2 = 0, h = 1;
        long seed = (long) (1e9 + 7);
        ListNode p = head;
        while (p != null) {
            hash1 = hash1 * seed + p.val;
            hash2 = hash2 + h * p.val;
            h *= seed;
            p = p.next;
        }
        return hash1 == hash2;
    }

    public int[] reversePrint(ListNode head) {
        List<Integer> answerList = new ArrayList<>();
        ListNode prev = null, tail = head;
        while(tail != null) {
            ListNode node = tail;
            tail = tail.next;
            node.next = prev;
            prev = node;
            answerList.add(prev.val);
        }
        Collections.reverse(answerList);
        int[] answer = new int[answerList.size()];
        for (int i=0; i<answer.length; ++i) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    /**
     * 反转链表:
     * 递归法完成反转链表
     * @param head 头指针
     * @return 返回反序之后的链表
     * 官方题解：
     *     public ListNode reverseList(ListNode head) {
     *         if (head == null || head.next == null) {
     *             return head;
     *         }
     *         ListNode p = reverseList(head.next);
     *         head.next.next = head;
     *         head.next = null;
     *         return p;
     *     }
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        //得到尾节点
        ListNode tail = head;
        while(tail.next!=null) {
            tail = tail.next;
        }
        helpReverse(head);
        //防止成环
        head.next = null;
        return tail;
    }

    private ListNode helpReverse(ListNode head) {
        //如果子节点为空，直接将head节点返回作为反序之后的父节点
        if (head.next != null) {
            //得到反序之后的子链表
            ListNode node = helpReverse(head.next);
            //将反序之后的节点的子节点设置为当前节点
            node.next = head;
        }
        return head;
    }

    /**
     * 反转链表迭代法：
     * 官方题解：
     *    public ListNode reverseList(ListNode head) {
     *         ListNode prev = null;
     *         ListNode curr = head;
     *         while (curr != null) {
     *             ListNode nextTemp = curr.next;
     *             curr.next = prev;
     *             prev = curr;
     *             curr = nextTemp;
     *         }
     *         return prev;
     *     }
     */
    public ListNode reverseListIteration(ListNode head) {
        if (head == null || head.next==null) return head;
        ListNode tail, itList, node;
        tail = head;
        itList = head.next;
        //防止成环
        head.next = null;

        while(itList!=null) {
            node = itList;
            itList = itList.next;
            node.next = tail;
            tail = node;
        }
        return tail;
    }


    /**
     * 重排序链表：
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * @param head 头节点指针
     */
    public void reorderList(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        for (int i=0; head != null; head=head.next, ++i) {
            map.put(i, head);
        }

        head = map.get(0);
        for (int i= map.size()-1; i> map.size()/2; --i) {
            ListNode node = head.next;
            ListNode last = map.get(i-1);
            head.next = last.next;
            head.next.next = node;
            head = node;
            last.next = null;
        }
    }

    /**
     * 双指针删除倒数第n个节点。
     * 其他方法：栈
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode first = sentinel, second = head;

        for (int i=1; i<n; ++i) {
            if (second == null) return sentinel.next;
            second = second.next;
        }

        while(second.next != null) {
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        return sentinel.next;
    }
}
