package org.example;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.reversePairs(new int[]{7, 5, 6, 4});
        System.out.println(i);
    }

    int[] buffer;
    final int KMOD = 1000000007;
    int answer = 0;

    public int reversePairs(int [] array) {
        buffer = new int[array.length];
        dfs(array, 0, array.length - 1);
        return answer;
    }

    private void dfs(int[] nums, int l, int r) {
        if (l >= r) return ;
        int mid = (r - l >> 1) + l;
        dfs(nums, l, mid);
        dfs(nums, mid + 1, r);
        merge(nums, l, r, mid);
    }

    private void merge(int[] nums, int l, int r, int mid) {
        int i = l, j = mid + 1;
        if (r - l + 1 >= 0) System.arraycopy(nums, l, buffer, l, r - l + 1);

        for (int k = l; k <= r; k++) {
            if (i > mid) nums[k] = buffer[j++];
            else if (j > r) nums[k] = buffer[i++];
            else if (buffer[i] <= buffer[j]) nums[k] = buffer[i++];
            else {
                answer += (mid - i + 1) % KMOD;
                nums[k] = buffer[j++];
            }
        }
    }
}
