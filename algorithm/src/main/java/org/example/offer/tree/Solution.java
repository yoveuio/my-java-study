package org.example.offer.tree;

import org.example.leetcode.TreeNode;

import java.util.*;

/**
 * @ClassName Solution
 * @Description 《剑指offer》树相关题目
 * @Author yoveuio
 * @Date 2020/11/1 16:22
 * @Version 1.0
 */
public class Solution {

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
}
