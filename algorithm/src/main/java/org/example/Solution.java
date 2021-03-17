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
     * stream示例
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean flag = solution.searchMatrix(new int[][]{{1, 1}}, 3);
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(-1);
            add(1);
        }};
        int[] ints = list.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.stream(ints).filter(i -> i > 0).forEach(System.out::println);
    }

    /**
     * 287. 寻找重复数
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            
        }
        return -1;
    }

    /**
     * 279. 完全平方数
     *
     * 动态规划五部曲：
     * 1. 确定dp数组，及下标意义
     * 2. 确定递推公式
     * 3. dp数组如何进行初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j--) {
                dp[i] = Math.min(dp[i - j * j], dp[i]);
            }
        }
        return dp[n];
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0) return false;
        int n = matrix.length, m = matrix[0].length;
        int l = 0, t = n - 1;

        while (l < m && t >= 0) {
            if (matrix[t][l] == target) return true;
            if (matrix[t][l] < target) {
                l++;
            } else {
                t--;
            }
        }
        return false;
    }

    /**
     * 删除数组中的重复元素
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode prev = head, curr = head.next;

        while (curr != null) {
            while (curr != null && prev.val == curr.val) {
                curr = curr.next;
            }
            prev.next = curr;
            prev = curr;
            curr = curr == null ? curr : curr.next;
        }
        return head;
    }

    /**
     * 删除数组中的重复元素2
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode curr = head;

        while (curr != null) {
            ListNode tmp = curr.next;
            boolean flag = false;
            while (tmp != null && tmp.val == curr.val) {
                flag = true;
                tmp = tmp.next;
            }

            if (flag) prev.next = tmp;
            else {
                prev.next = curr;
                prev = prev.next;
            }
            curr = tmp;
        }
        return sentinel.next;
    }

    /**
     *  162. 寻找峰值
     */
    public int findPeakElement(int[] nums){
        if (nums == null || nums.length < 2 || nums[0] > nums[1]) return 0;
        int n = nums.length;
        if (nums[n - 2] < nums[n - 1]) return n - 1;
        int l = 0, r = n - 1, mid;
        while (l < r) {
            mid = (r - l >> 1) + l;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    /**
     * 46. 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        answers = new ArrayList<>();
        path = new ArrayList<>();
        map = new HashMap<>();
        len = nums.length;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        permuteHandler(0);
        return answers;
    }

    private void permuteHandler(int height) {
        if (height == len) {
            answers.add(new ArrayList<>(path));
            return;
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry: entries) {
            int k = entry.getKey();
            int v = entry.getValue();
            if (map.get(k) == 0) continue;
            path.add(k);
            map.put(k, v - 1);
            permuteHandler(height + 1);
            map.put(k, v);
            path.remove(height);
        }
    }

    int len;
    List<List<Integer>> answers = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    /**
     * 39. 组合总和
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
        }
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     */
    public int getMinimumDifference(TreeNode root) {
        int prev = -1;
        Deque<TreeNode> stack = new ArrayDeque<>();
        int ans = Integer.MAX_VALUE;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != -1)
                ans = Math.min(ans, root.val - prev);
            prev = root.val;
            root = root.right;
        }
        return ans;
    }

    /**
     * 144. 二叉树的前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                list.add(root.val);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return list;
    }

    /**
     * 最长回文子串
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int begin = 0;
        int length = 1;

        for (int len = 0; len < n; len++) {
            for (int i = 0; i < n; i++) {
                int j = i + len;
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                    continue;
                }
                if (len < 3) dp[i][j] = true;
                else dp[i][j] = dp[i + 1][j - 1];
                if (dp[i][j] && j - i + 1 > length) {
                    begin = i;
                    length = len + 1;
                }
            }
        }
        return s.substring(begin, begin + length);
    }

    /**
     * 151. 翻转字符串里的单词
     */
    public String reverseWords(String s) {
        if (s == null || (s = s.trim()).length() == 0) return "";
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; ) {
            int j = i - 1;
            while (j >= 0 && s.charAt(j) != ' ') j--;
            sb.append(s, j + 1, i).append(' ');
            while (s.charAt(j) == ' ') j--;
            i = j + 1;
        }
        return sb.toString().trim();
    }

    /**
     * 98. 验证二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    /**
     * 中序方式
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long prev = Long.MIN_VALUE;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= prev) return false;
            prev = root.val;
            root = root.right;
        }
        return true;
    }


    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums   数组
     * @param target 目标值
     * @return 返回目标值所在索引
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n <= 0) return -1;
        int l = 0, r = n - 1, mid;
        int v = nums[0];
        while (l <= r) {
            mid = (r - l >> 1) + l;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= v) {
                if (target >= v && nums[mid] > target) r = mid - 1;
                else l = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[n - 1]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 路径之和
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) return answers;
        pathSumHelper(root, sum, answers, path, 0);
        return answers;
    }

    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> answers,
                               List<Integer> path, int height) {
        if (root.right == null && root.left == null) {
            if (sum != root.val) return;
            path.add(root.val);
            answers.add(new ArrayList<>(path));
            path.remove(height);
            return;
        }
        path.add(root.val);
        if (root.left != null)
            pathSumHelper(root.left, sum - root.val, answers, path, height + 1);
        if (root.right != null)
            pathSumHelper(root.right, sum - root.val, answers, path, height + 1);
        path.remove(height);
    }

    /**
     * 缺失的第一个正数
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 接雨水
     */
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = height.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int distance = i - stack.peek() - 1;
                ans += distance * (Math.min(height[i], height[stack.peek()]) - height[top]);
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 124. 二叉树中的最大路径和
     */
    public int maxPathSum(TreeNode root) {
        intAns = Integer.MIN_VALUE;
        _maxPathSum(root);
        return intAns;
    }

    private int _maxPathSum(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(_maxPathSum(node.left), 0);
        int right = Math.max(_maxPathSum(node.right), 0);
        int ans = left + right + node.val;
        intAns = Math.max(intAns, ans);
        return Math.max(left, right) + node.val;
    }

    /**
     * 两个栈实现队列
     */
    static class MyStack {

        Queue<Integer> stack;
        Queue<Integer> help;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            stack = new ArrayDeque<>();
            help = new ArrayDeque<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            help.offer(x);
            while (!stack.isEmpty()) {
                help.offer(stack.poll());
            }
            Queue<Integer> tmp = stack;
            stack = help;
            help = tmp;
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return stack.isEmpty() ? -1 : stack.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return stack.isEmpty() ? -1 : stack.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return stack.isEmpty();
        }
    }

    /**
     * 102. 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> answers = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> answer = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                answer.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            answers.add(answer);
        }
        return answers;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return _buildTree(preorder, inorder, 0, preorder.length - 1);
    }

    int count;

    private TreeNode _buildTree(int[] preorder, int[] inorder, int lo, int hi) {
        if (lo > hi) return null;
        int i = lo;
        int rootValue = preorder[count++];
        TreeNode root = new TreeNode(rootValue);
        while (rootValue != inorder[i]) i++;
        root.left = _buildTree(preorder, inorder, lo, i - 1);
        root.right = _buildTree(preorder, inorder, i + 1, hi);
        return root;
    }

    /**
     * 110. 平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        return _isBalanced(root) != -1;
    }

    private int _isBalanced(TreeNode node) {
        if (node == null) return 0;
        int left = _isBalanced(node.left);
        if (left == -1) return -1;
        int right = _isBalanced(node.right);
        if (right == -1) return -1;
        return Math.abs(right - left) > 1 ? -1 : Math.max(right, left) + 1;
    }

    /**
     * 判断链表是否有环
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 104. 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 142. 环形链表 II
     *
     * @param head 给定一个链表
     * @return 返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * 88. 合并两个有序数组
     *
     * @param nums1 数组
     * @param m     nums1数组元素个数
     * @param nums2 数组
     * @param n     nums2数组元素个数
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        int[] copy = new int[len];

        for (int i = 0; i < len; i++) {

        }
    }


    /**
     * 543. 二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        depthOfBinaryTree(root);
        return intAns;
    }

    int intAns;

    private int depthOfBinaryTree(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = depthOfBinaryTree(node.left);
        int rightDepth = depthOfBinaryTree(node.right);
        intAns = Math.max(intAns, rightDepth - leftDepth);
        return Math.max(rightDepth, leftDepth) + 1;
    }


    /**
     * 92. 反转链表 II
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode sentinel = new ListNode(-1);
        ListNode prev = sentinel;
        sentinel.next = head;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
            head = head.next;
        }

        int n = right - left;
        ListNode tail = head;
        for (int i = 0; i < n; i++) {
            tail = tail.next;
        }
        ListNode next = tail.next;

        ListNode[] nodes = reverseBetween(head, tail);
        prev.next = nodes[0];
        nodes[1].next = next;

        return sentinel.next;
    }

    private ListNode[] reverseBetween(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode curr = head;

        while (prev != tail) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return new ListNode[]{tail, head};
    }

    /**
     * 54. 螺旋矩阵
     *
     * @param matrix 矩阵
     * @return 返回翻转后的矩阵
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return answer;
        int l = 0, t = 0, b = matrix.length - 1, r = matrix[0].length - 1;
        while (true) {
            for (int i = l; i <= r; i++) answer.add(matrix[t][i]);
            if (++t > b) break;
            for (int i = t; i <= b; i++) answer.add(matrix[i][r]);
            if (--r < l) break;
            for (int i = r; i >= l; i--) answer.add(matrix[b][i]);
            if (--b < t) break;
            for (int i = b; i >= t; i--) answer.add(matrix[i][l]);
            if (++l > r) break;
        }
        return answer;
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int sell = 0, buy = -prices[0];

        for (int price : prices) {
            buy = Math.max(buy, -price);
            sell = Math.max(sell, buy + price);
        }

        return sell;
    }

    /**
     * 20. 有效的括号
     *
     * @param s 括号组成的字符串
     * @return 返回是否是有效的括号
     */
    public boolean isValid(String s) {
        if ((s.length() & 1) != 0) return false;
        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('}', '{');
            put(']', '[');
            put(')', '(');
        }};
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) stack.push(c);
            else if (map.get(c) != stack.poll()) return false;
        }

        return stack.isEmpty();
    }

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
                return;
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
            i--;
            j--;
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
            while (++lt <= hi && nums[lt] >= v) ;
            while (--gt >= lo && nums[gt] < v) ;
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
            if (sqrt == x) return (int) mid;
            else if (sqrt > x) r = mid - EXP;
            else l = mid + EXP;
        }
        int y = (int) mid + 1;
        if (y * y == x) return y;
        return (int) mid;
    }
}
