package org.example.tree;

import org.example.leetcode.ListNode;
import org.example.leetcode.TreeNode;

import java.util.HashMap;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/18 10:04
 * @Version 1.0
 */
public class Solution {

    /**
     * 通过前序遍历和中序遍历确定一颗二叉树
     */
    int root;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        root = 0;
        return buildTree(0, preorder.length-1, preorder, inorder);
    }

    private TreeNode buildTree(int l, int r, int[] preorder, int[] inorder){
        if (l > r || root >= preorder.length) return null;
        TreeNode node = new TreeNode(preorder[root]);
        for (int i = l; i <= r; ++i) {
            if (root < preorder.length && preorder[root] == inorder[i]) {
                root++;
                node.left = buildTree(l, i-1, preorder, inorder);
                node.right = buildTree(i+1, r, preorder, inorder);
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
