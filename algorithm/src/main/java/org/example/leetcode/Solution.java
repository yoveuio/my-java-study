package org.example.leetcode;

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
        /*Solution solution = new Solution();
        int i = solution.canCompleteCircuit(new int[]{3, 3, 4}, new int[]{3, 4, 4});
        System.out.println(i);*/
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> answer, prev;
        if (numRows == 0) return answers;

        answer = new ArrayList<>();
        answer.add(1);
        answers.add(answer);
        for (int i = 1; i < numRows; i++) {
            answer = new ArrayList<>();
            prev = answers.get(i - 1);
            answer.add(1);

            for (int j = 0; j < prev.size() - 1; j++){
                answer.add(prev.get(j) + prev.get(j + 1));
            }
            answer.add(1);
            answers.add(answer);
        }
        return answers;
    }

    public int largestPerimeter(int[] A) {
        //largestPerimeterHandler(A, 0, A.length-1);
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; --i) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }

    private void largestPerimeterHandler(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi+1;
        int v = a[lo];
        while (true) {
            while (a[++i] > v && i < hi);
            while (a[--j] < v && j > lo);
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        largestPerimeterHandler(a, lo, j-1);
        largestPerimeterHandler(a, j+1, hi);
    }

    private void swap(int[] a, int i, int j) {
        if (i == j) return;
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }

    /**
     * 加油站
     * @param gas 第i能补充的油
     * @param cost 第i到i+1消耗的油
     * @return 返回能够走完一个环的第一个起点
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; ++i) {
            gas[i] -= cost[i];
        }

        label:
        for (int i = 0; i < gas.length;) {
            int carry = 0;
            int count = 0;
            while (count < gas.length) {
                carry += gas[(count + i) % gas.length];
                if (carry < 0) {
                    i = i+count+1;
                    continue label;
                }
                count++;
            }
            return i;
        }
        return -1;
    }

    /**
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     * 另外，我们在该矩阵中给出了一个坐标为(r0, c0) 的单元格。
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
     * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] answer = new int[(R-1) * (C -1)][];
        int count = 0;
        for (int i=0; i<R; ++i) {
            for (int j=0; j<C; ++j) {
                int[] point = new int[]{i, j};
                answer[count++] = point;
            }
        }
        Arrays.sort(answer, Comparator.comparingInt(o -> (Math.abs(o[0] - r0) + Math.abs(o[1] - c0))));
        return answer;
    }

    /**
     * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小
     * 单调栈+贪心解法
     */
    public String removeKDigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c: num.toCharArray()) {
            while (k != 0 && !stack.isEmpty() && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        for (int i=0; i<k; ++i) {
            stack.pop();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c: stack) {
            stringBuilder.append(c);
        }
        stringBuilder.reverse();
        int index = 0;
        for (char c: stringBuilder.toString().toCharArray()) {
            if (c != '0') {
                break;
            }
            else index++;
        }
        String answer = stringBuilder.substring(index);
        return Objects.equals(answer, "") ? "0" : answer;
    }

    /**
     * 最大的整数次方
     *  快速幂：
     *      通过二分/二进制的性质，求x^n
     *          n = f(n) = 1*b1 + 2*b2 + …… + 2^(m-1)*bm
     *          其中
     */
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

    /**
     * 广度优先遍历BFS
     *  机器人的运动范围
     * @param m 行
     * @param n 列
     * @param k 运动范围
     * @return 能到达的格子数
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] vis = new boolean[m][n];
        int ans = 1;
        vis[0][0] = true;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }
                // 边界判断
                if (i - 1 >= 0) {
                    vis[i][j] |= vis[i - 1][j];
                }
                if (j - 1 >= 0) {
                    vis[i][j] |= vis[i][j - 1];
                }
                ans += vis[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    /**
     * 找到数组中的第一个重复数字
     * 进阶考点：原地Hash
     * @param nums 给定的一个数组
     * @return 任意一个重复数字
     */
    public int findRepeatNumber(int[] nums) {
        int temp;
        for (int i=0; i<nums.length; ++i) {
            while(nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }


    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
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

}
