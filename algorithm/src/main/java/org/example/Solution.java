package org.example;

import org.example.leetcode.ListNode;
import org.example.leetcode.TreeNode;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 字节高频题
 * @date 2021/1/9 9:20
 */
@SuppressWarnings("unused")
class Solution {

    /**
     * LRUCache的设计
     */
    static class LRUCache {
        int capacity;
        Map<Integer, Node> map;
        Deque<Node> deque;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            deque = new LinkedList<>();
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            nodeToTail(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (get(key) != -1) {
                map.get(key).value = value;
                return ;
            }
            if (capacity == deque.size()) {
                Node node = deque.removeFirst();
                map.remove(node.key);
            }
            Node node = new Node(key, value);
            deque.addLast(node);
            map.put(key, node);
        }

        public void nodeToTail(Node node) {
            deque.remove(node);
            deque.addLast(node);
        }

        static class Node {
            int key;
            int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    /**
     * 二叉树的右视图
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = null;
            for (int i = queue.size(); i > 0; i--) {
                node = queue.poll();
                assert node != null;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            answer.add(node.val);
        }
        return answer;
    }

    /**
     * 最大子序和
     */
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] > 0 ? nums[i - 1] + nums[i] : nums[i];
            ans = Math.max(ans, nums[i]);
        }
        return ans;
    }

    /**
     * 415. 字符串相加
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + carry;
            sb.append(result % 10);
            carry = result / 10;
            i--; j--;
        }
        return sb.reverse().toString();
    }

    /**
     * 二叉树的公共祖先
     */
    TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        _lowestCommonAncestor(root, p, q);
        return ans;
    }

    private boolean _lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return false;
        boolean lSon = _lowestCommonAncestor(node.left, p, q);
        boolean rSon = _lowestCommonAncestor(node.right, p, q);
        if ((lSon && rSon) || ((node.val == p.val || node.val == q.val) && (lSon || rSon))) {
            ans = node;
        }
        return lSon || rSon || node.val == p.val || node.val == q.val;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> answers = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> answer = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                assert node != null;
                if ((answers.size() & 1) == 0) answer.addFirst(node.val);
                else answer.addLast(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            answers.add(answer);
        }
        return answers;
    }

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

    @SuppressWarnings("all")
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
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

    /**
     * 69. x 的平方根
     */
    public double mySqrt(int x) {
        final double EXP = 10e-7;

        double l = 0, r = x, mid = x;
        while (r >= l && Math.abs(r - l) > EXP) {
            mid = (r - l) / 2 + l;
            double sqrt = mid * mid;
            if (sqrt == x) return (int)mid;
            else if (sqrt > x) r = mid - EXP;
            else l = mid + EXP;
        }
        int y = (int)mid + 1;
        if (y * y == x) return y;
        return (int)mid;
    }
}
