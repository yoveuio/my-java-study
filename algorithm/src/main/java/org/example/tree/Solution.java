package org.example.tree;

import org.example.leetcode.Node;
import org.example.leetcode.TreeNode;

import java.util.*;

/**
 * @ClassName Solution
 * @Description 树章节力扣题
 * @Author yoveuio
 * @Date 2020/10/18 10:04
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.verifyPostorder(new int[]{4, 6, 7, 5});
    }
    /* -------------------------------常量区---------------------------------------- */
    TreeNode ansTree;

    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 说明:
     *      - 所有节点的值都是唯一的。
     *      - p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 题解：如果一个节点的左右子树同时找到了两个点，那么这个节点一定是最近的点
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof
     * @param root 树的根节点
     * @param p 指定的节点
     * @param q 同上
     * @return 返回最近的公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorHandler(root, p, q);
        return ansTree;
    }

    private boolean lowestCommonAncestorHandler(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = lowestCommonAncestorHandler(root.left, p, q);
        boolean rson = lowestCommonAncestorHandler(root.right, p, q);
        if ((lson && rson) || ((lson || rson) && (root.val == p.val || root.val == q.val))) {
            ansTree = root;
        }
        return lson || rson || root.val == p.val || root.val == q.val;
    }

    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 说明:
     *      - 所有节点的值都是唯一的。
     *      - p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorHandler2(root, p, q);
        return ansTree;
    }

    private void lowestCommonAncestorHandler2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return;
        if (p.val < root.val && q.val < root.val) lowestCommonAncestorHandler2(root.left, p, q);
        else if (p.val > root.val && q.val > root.val) lowestCommonAncestorHandler2(root.right, p, q);
        else ansTree = root;
    }


    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
     * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedHandler(root) != -1;
    }

    private int isBalancedHandler(TreeNode node) {
        if (node == null) return 0;
        int left = isBalancedHandler(node.left);
        if (left == -1) return -1;
        int right = isBalancedHandler(node.right);
        if (right == -1) return -1;
        return Math.abs(left - right) >= 2 ? -1 : Math.max(left, right) + 1;
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

    /**
     * 剑指55 二叉树的深度
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 剑指offer 34. 二叉树中和为某一值的路径
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径
     * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * @param root 根节点
     * @param sum 目标值
     * @return 返回所有满足条件的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        answers = new LinkedList<>();
        path = new LinkedList<>();
        pathSumHandler(root, sum, 0, 0);
        return answers;
    }

    List<Integer> path;
    List<List<Integer>> answers;

    private void pathSumHandler(TreeNode node, int sum, int value, int index) {
        if (node == null) return;
        if (isLeaf(node)) {
            if (value+node.val == sum) {
                List<Integer> answer = new ArrayList<>(path);
                answer.add(node.val);
                answers.add(answer);
            }
            return;
        }

        path.add(node.val);
        pathSumHandler(node.left, sum, value+node.val, index+1);
        pathSumHandler(node.right, sum, value+ node.val, index+1);
        path.remove(index);
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }


    /**
     * 判断一个序列是否是一颗树的后序序列
     * @param postorder 一个序列
     * @return 如果是一棵树的后序遍历序列返回true，否则返回false
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorderHandler(postorder, 0, postorder.length-1);
    }

    private boolean verifyPostorderHandler(int[] postorder, int start, int root) {
        if (start >= root) return true;
        int i = start - 1;
        int v = postorder[root];
        while (++i < v && postorder[i] < v);
        int j = i;
        while (++i < v && postorder[i] > v);
        return i >= root && verifyPostorderHandler(postorder, start, j - 1) && verifyPostorderHandler(postorder
                , j, root - 1);
    }

    Node prev, head;
    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     * @param root 树的根节点
     * @return 返回最小的节点
     */
    public Node treeToDoublyList(Node root) {
        treeToDoublyListHandler(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    private void treeToDoublyListHandler(Node curr) {
        if (curr == null) return ;
        treeToDoublyListHandler(curr.left);
        if (prev != null) prev.right = curr;
        else head = curr;
        curr.left = prev;
        prev = curr;
        treeToDoublyListHandler(curr.right);
    }

    /**
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders(TreeNode root) {
        // write code here
        int[][] answers = new int[3][];
        answers[0] = preorder(root);
        answers[1] = inorder(root);
        answers[2] = postorder(root);
        return answers;
    }

    private int[] preorder(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            answer.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int[] postorder(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            answer.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        Collections.reverse(answer);
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int[] inorder(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            answer.add(root.val);
            root = root.right;
        }
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }

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
            if (post[i1 + L - 1] == pre[i0 + 1])
                break;

        root.left = make(i0 + 1, i1, L);
        root.right = make(i0 + L + 1, i1 + L, N - 1 - L);
        return root;
    }


    /**
     * 之字形打印二叉树
     *
     * @param root 根节点
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {

        Map<String, Map<String, String>> map = new HashMap();

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
                else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
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
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * 层次打印二叉树
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
        for (int i = 0; i < answerList.size(); ++i) {
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
     *
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
     * isSubStructure用来遍历A树，来找到一个头节点和B树开始匹配
     * isSubStructureHandler用来判断A、B俩树是否相同，如果A有一颗子树和B相同，就为true
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
}
