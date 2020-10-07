package org.example.leetcode;

/**
 * @ClassName TreeNode
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/21 8:29
 * @Version 1.0
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
