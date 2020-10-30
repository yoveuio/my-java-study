package org.example.tree;

import org.example.leetcode.TreeNode;

/**
 * @ClassName TreeTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/18 11:22
 * @Version 1.0
 */
public class TreeTest {

    static Solution solution = new Solution();

    public static void main(String[] args) {

        //solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        solution.sumNumbers(treeNode);
    }
}
