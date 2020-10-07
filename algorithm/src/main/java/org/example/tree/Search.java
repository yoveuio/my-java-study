package org.example.tree;

import org.example.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Search
 * @Description https://blog.csdn.net/sinat_36412790/article/details/80299799
 * @Author yoveuio
 * @Date 2020/9/14 19:49
 * @Version 1.0
 */
public class Search {


    /**
     * 用栈实现二叉树的前序遍历
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        TreeNode first = root;
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> answer = new LinkedList<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            answer.add(node.val);

            if (node.right!=null) {
                stack.push(node.right);
            }
            if (node.left!=null) {
                stack.push(node.left);
            }
        }
        return answer;
    }

    /**
     * 用栈实现二叉树的中序遍历
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        return null;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(Search.preorderTraversal(root));
    }

}
