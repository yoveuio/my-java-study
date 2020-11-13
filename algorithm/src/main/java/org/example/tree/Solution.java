package org.example.tree;

import org.example.leetcode.ListNode;
import org.example.leetcode.TreeNode;

import java.util.*;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/18 10:04
 * @Version 1.0
 */
public class Solution {

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
            if (post[i1 + L-1] == pre[i0 + 1])
                break;

        root.left = make(i0+1, i1, L);
        root.right = make(i0+L+1, i1+L, N-1-L);
        return root;
    }

    /**
     * 判断是不是树的后序遍历结果
     *
     * 后序链表的遍历结果的顺序是左右根，所有比最后一点小的数都是左节点，所有比最后一点大的数都是右子树节点、
     * 最后只需要通过分治验证右子树是否都比根节点大即可
     *
     * 单调栈解法
     * public boolean verifyPostorder(int[] postorder) {
     *     Stack<Integer> stack = new Stack<>();
     *     int root = Integer.MAX_VALUE;
     *     for(int i = postorder.length - 1; i >= 0; i--) {
     *         if(postorder[i] > root) return false;
     *         while(!stack.isEmpty() && stack.peek() > postorder[i])
     *             root = stack.pop();
     *         stack.add(postorder[i]);
     *     }
     *     return true;
     * }
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }


    boolean recur(int[] postorder, int i, int j) {
        if(i >= j) return true;
        int p = i;
        while(postorder[p] < postorder[j]) p++;
        int m = p;
        while(postorder[p] > postorder[j]) p++;
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    /**
     * 之字形打印二叉树
     * @param root 根节点
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {

        Map<String, Map<String, String>> map = new HashMap();

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if(res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
                else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
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
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * 层次打印二叉树
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
        for (int i = 0; i<answerList.size(); ++i) {
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
     *  isSubStructure用来遍历A树，来找到一个头节点和B树开始匹配
     *  isSubStructureHandler用来判断A、B俩树是否相同，如果A有一颗子树和B相同，就为true
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
/*    HashMap<Integer, Integer> dic = new HashMap<>();
    int[] po;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        po = preorder;
        for (int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int pre_root, int in_left, int in_right) {
        if (in_left > in_right) return null;
        TreeNode root = new TreeNode(po[pre_root]);
        int i = dic.get(po[pre_root]);
        root.left = recur(pre_root + 1, in_left, i - 1);
        root.right = recur(pre_root + i - in_left + 1, i + 1, in_right);
        return root;
    }*/
}
