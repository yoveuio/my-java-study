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
     * 剑指 Offer 52. 两个链表的第一个公共节点
     * (https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)
     * 输入两个链表，找出它们的第一个公共节点。
     * 浪漫相遇?
     * @param headA 链表A
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pointA = headA;
        ListNode pointB = headB;

        while (pointA != pointB) {
            pointA = pointA != null ? pointA.next : headB;
            pointB = pointB != null ? pointB.next : headA;
        }

        return pointA;
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
