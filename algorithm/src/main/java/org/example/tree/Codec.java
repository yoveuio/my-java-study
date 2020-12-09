package org.example.tree;

import org.example.leetcode.TreeNode;

import java.util.*;

/**
 * @ClassName Codec
 * @Description 二叉树的序列和反序列
 * @Author yoveuio
 * @Date 2020/12/6 15:46
 * @Version 1.0
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<TreeNode>() {{ add(root); }};
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                res.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (data.length() == 0) return null;
        String[] strings = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));

        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!strings[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(strings[i]));
                queue.add(node.left);
            }
            i++;
            if (strings[i++].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(strings[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}