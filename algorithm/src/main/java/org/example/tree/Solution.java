package org.example.tree;

import org.example.leetcode.Node;
import org.example.leetcode.TreeNode;

import java.util.*;

/**
 * @ClassName Solution
 * @Description 树章节力扣题
 * @Author yoveuio
 * @Date 2020/10/18 10:04
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.verifyPostorder(new int[]{4, 6, 7, 5});
    }

    /**
     * 剑指offer 34. 二叉树中和为某一值的路径
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径
     * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * @param root 根节点
     * @param sum 目标值
     * @return 返回所有满足条件的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        answers = new LinkedList<>();
        path = new LinkedList<>();
        pathSumHandler(root, sum, 0, 0);
        return answers;
    }

    List<Integer> path;
    List<List<Integer>> answers;

    private void pathSumHandler(TreeNode node, int sum, int value, int index) {
        if (node == null) return;
        if (isLeaf(node)) {
            if (value+node.val == sum) {
                List<Integer> answer = new ArrayList<>(path);
                answer.add(node.val);
                answers.add(answer);
            }
            return;
        }

        path.add(node.val);
        pathSumHandler(node.left, sum, value+node.val, index+1);
        pathSumHandler(node.right, sum, value+ node.val, index+1);
        path.remove(index);
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }


    /**
     * 判断一个序列是否是一颗树的后序序列
     * @param postorder 一个序列
     * @return 如果是一棵树的后序遍历序列返回true，否则返回false
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorderHandler(postorder, 0, postorder.length-1);
    }

    private boolean verifyPostorderHandler(int[] postorder, int start, int root) {
        if (start >= root) return true;
        int i = start - 1;
        int v = postorder[root];
        while (++i < v && postorder[i] < v);
        int j = i;
        while (++i < v && postorder[i] > v);
        return i >= root && verifyPostorderHandler(postorder, start, j - 1) && verifyPostorderHandler(postorder
                , j, root - 1);
    }

    Node prev, head;
    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     * @param root 树的根节点
     * @return 返回最小的节点
     */
    public Node treeToDoublyList(Node root) {
        treeToDoublyListHandler(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    private void treeToDoublyListHandler(Node curr) {
        if (curr == null) return ;
        treeToDoublyListHandler(curr.left);
        if (prev != null) prev.right = curr;
        else head = curr;
        curr.left = prev;
        prev = curr;
        treeToDoublyListHandler(curr.right);
    }

    /**
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders(TreeNode root) {
        // write code here
        int[][] answers = new int[3][];
        answers[0] = preorder(root);
        answers[1] = inorder(root);
        answers[2] = postorder(root);
        return answers;
    }

    private int[] preorder(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            answer.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int[] postorder(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            answer.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        Collections.reverse(answer);
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int[] inorder(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            answer.add(root.val);
            root = root.right;
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 前序后序确定二叉树
     */
    int[] pre, post;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return make(0, 0, pre.length);
    }

    public TreeNode make(int i0, int i1, int N) {
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[i0]);
        if (N == 1) return root;

        int L = 1;
        for (; L < N; ++L)
            if (post[i1 + L - 1] == pre[i0 + 1])
                break;

        root.left = make(i0 + 1, i1, L);
        root.right = make(i0 + L + 1, i1 + L, N - 1 - L);
        return root;
    }


    /**
     * 之字形打印二叉树
     *
     * @param root 根节点
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {

        Map<String, Map<String, String>> map = new HashMap();

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
                else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * 层次打印二叉树，并将结果每一层的结果单独存储
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * 层次打印二叉树
     *
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> answerList = new ArrayList<>();
        int[] answer;
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            answerList.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); ++i) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    /**
     * 判断是否是对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return isSymmetricHandler(root.left, root.right);
    }

    private boolean isSymmetricHandler(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetricHandler(left.right, right.left) && isSymmetricHandler(left.left, right.right);
    }

    /**
     * 镜像二叉树，将左右子树替换
     *
     * @param root 根节点
     * @return 返回镜像二叉树
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = mirrorTree(root.right);
        root.right = mirrorTree(root.left);
        root.left = temp;
        return root;
    }

    /**
     * 判断B树是不是A树的子结构
     * isSubStructure用来遍历A树，来找到一个头节点和B树开始匹配
     * isSubStructureHandler用来判断A、B俩树是否相同，如果A有一颗子树和B相同，就为true
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;
        return isSubStructureHandler(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSubStructureHandler(TreeNode a, TreeNode b) {
        if (b == null) return true;
        if (a == null || a.val != b.val) return false;
        return isSubStructureHandler(a.left, b.left) && isSubStructureHandler(a.right, b.right);
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    /**
     * 通过前序遍历和中序遍历确定一颗二叉树
     */
    int root;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        root = 0;
        return buildTree(0, preorder.length - 1, preorder, inorder);
    }

    private TreeNode buildTree(int l, int r, int[] preorder, int[] inorder) {
        if (l > r || root >= preorder.length) return null;
        TreeNode node = new TreeNode(preorder[root]);
        for (int i = l; i <= r; ++i) {
            if (root < preorder.length && preorder[root] == inorder[i]) {
                root++;
                node.left = buildTree(l, i - 1, preorder, inorder);
                node.right = buildTree(i + 1, r, preorder, inorder);
            }
        }
        return node;
    }
}
