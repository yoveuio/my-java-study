package org.example.tree;

import org.example.leetcode.TreeNode;

import java.util.*;

/**
 * @ClassName Search
 * @Description https://blog.csdn.net/sinat_36412790/article/details/80299799
 * @Author yoveuio
 * @Date 2020/9/14 19:49
 * @Version 1.0
 */
@SuppressWarnings("unused")
public class Search {
    /**
     * 用栈实现二叉树的前序遍历
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
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

    public static List<Integer> preorderTraversal2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> answer = new LinkedList<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                answer.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return answer;
    }

    /**
     * 用栈实现二叉树的中序遍历
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> answer = new LinkedList<>();

        while(!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            answer.add(root.val);
            root = root.right;
        }

        return answer;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> answer = new LinkedList<>();

        stack.push(root);
        while(!stack.isEmpty()) {
            root = stack.pop();
            answer.add(root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        Collections.reverse(answer);
        return answer;
    }

    public static List<Integer> postorderTraversal2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> answer = new ArrayList<>();

        while(!stack.isEmpty() || root !=null) {
            while (root != null) {
                answer.add(root.val);
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            root = root.left;
        }
        Collections.reverse(answer);
        return answer;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(Search.postorderTraversal(root));
    }

}
