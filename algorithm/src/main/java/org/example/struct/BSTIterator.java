package org.example.struct;

import org.example.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yoveuio
 * @version 1.0
 * @className BSTIterator
 * @description
 *  173. 二叉搜索树迭代器
 * @date 2021/3/28 9:24
 */
@SuppressWarnings("unused")
class BSTIterator {
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode root;

    public BSTIterator(TreeNode root) {
        this.root = root;
    }

    public int next() {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        int ans = root.val;
        root = root.right;
        return ans;
    }

    public boolean hasNext() {
        return root != null || !stack.isEmpty();
    }
}