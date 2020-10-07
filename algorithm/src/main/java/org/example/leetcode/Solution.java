package org.example.leetcode;

import org.jetbrains.annotations.NotNull;

import java.net.Socket;
import java.util.*;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/17 8:48
 * @Version 1.0
 */
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Node sentinel, point;

        sentinel = new Node(-1);
        queue.add(root);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            point = sentinel;
            while(node.next!=null) {
                if (node.left != null) {
                    point.next = node.left;
                    point = point.next;
                }
                if (node.right != null) {
                    point.next = node.right;
                    point = point.next;
                }
            }
            point.next = null;
            queue.add(sentinel);
        }
        return root;
    }

    List<List<Integer>> answers;
    int[] path;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        answers = new ArrayList<>();
        path = new int[1<<5];
        if (root == null) {
            return answers;
        }
        pathSum(root, sum, 0);
        return answers;
    }

    private void pathSum(TreeNode node, int number, int height) {
        if (number-node.val == 0 && node.left==null && node.right==null) {
            List<Integer> answer = new ArrayList<>();
            path[height] = node.val;
            for (int i = 0; i <= height; i++) {
                answer.add(path[i]);
            }
            answers.add(answer);
            return;
        }
        path[height] = node.val;
        if (node.left != null && number >= 0) pathSum(node.left, number-node.val, height+1);
        if (node.right != null && number >= 0) pathSum(node.right, number-node.val, height+1);
    }

    /**
     * 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象
     * 计算监控树的所有节点所需的最小摄像头数量
     *
     * 链接：https://leetcode-cn.com/problems/binary-tree-cameras/solution/jian-kong-er-cha-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * @param root 树的头节点
     * @return 所需要的最少的监控
     */
    public int minCameraCover(TreeNode root) {
        return cameraCoverSearch(root)[1];
    }

    /**
     * 定义三个状态进行树之间的状态转移:a, b, c三个状态
     *  a状态：当前节点放置监控的情况下需要的监控
     *  b状态：当前节点被覆盖(可以有或者没有监控)的情况下需要的监控
     *  c状态：当前节点不被覆盖(可以有或者没有被覆盖)的情况下需要的监控
     *
     *  状态转移：
     *      a状态：当左右节点没有被覆盖的时候，当前节点必须放置摄像机。由左右节点的c状态转义而来
     *          a = lc + rc + 1
     *      b状态：
     *          1.左节点有监控，右节点没有监控：左节点的a状态+右节点的b状态
     *              b = la + rb
     *          2.右节点有监控，左节点没有监控：左节点的b状态+右节点的a状态
     *              b = lb + ra
     *          3.左右节点都有监控
     *              b = la + ra (由定义可以看出a >= b, 因此这个状态实际上被上面两种包含进去了)
     *          4.左右节点都没有监控，自己有监控
     *              b = a
     *      c状态：
     *          1.左右节点没有被覆盖
     *              c = a
     *          2.左右节点被覆盖了
     *              c = lb + rb
     *   最后的b状态即是需要的数量(保证所有节点被覆盖)
     * @param node 进行分析的节点
     * @return 将分析之后的三个状态返回给上一个栈
     */
    private int[] cameraCoverSearch(TreeNode node){
        if (node == null) {
            /* 如果遍历到底端，空节点默认为被覆盖。不影响上一节点
             */
            return new int[]{Integer.MAX_VALUE >> 1, 0, 0};
        }
        //后序遍历二叉树
        int[] leaf = cameraCoverSearch(node.left);
        int[] right = cameraCoverSearch(node.right);
        int[] arr = new int[3];

        arr[0] = leaf[2] + right[2] + 1;
        arr[1] = Math.min(arr[0], Math.min(leaf[0]+right[1], leaf[1]+right[0]));
        arr[2] = Math.min(arr[0], leaf[1]+right[1]);

        return arr;
    }

    List<List<Integer>> answer = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        answer.add(new ArrayList<>());
        //dfs(nums, 0);
        return answer;
    }

    private void dfs(int[] nums, int i) {
        answer.add(new ArrayList<Integer>(){{
                add(1);
        }});
    }
}
