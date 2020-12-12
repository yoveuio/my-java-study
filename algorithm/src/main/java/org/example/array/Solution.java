package org.example.array;

import java.util.*;

/**
 * @ClassName Solution
 * @Description leetcode算法章节的题目
 * @Author yoveuio
 * @Date 2020/10/14 18:56
 * @Version 1.0
 */
public class Solution {

    /**
     * 剑指51 逆序数组
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     * @param nums 一个数组，数组长度：0-50000
     * @return 逆序对的数量
     */
    public int reversePairs(int[] nums) {
        if (nums.length < 2) return 0;
        aux = new int[nums.length];
        return reversePairsHandler(nums, 0, nums.length-1);
    }
    int[] aux;

    private int reversePairsHandler(int[] nums, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = (hi - lo >> 1) + lo;
        int count = 0;
        //左边的逆序对数量
        count += reversePairsHandler(nums, lo, mid);
        //右边的逆序对数量
        count += reversePairsHandler(nums, mid + 1, hi);
        //两个区间中间的逆序对数量
        return merge(nums, lo, hi, mid) + count;
    }

    private int merge(int[] nums, int lo, int hi, int mid) {
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(nums, lo, aux, lo, hi + 1 - lo);
        int count = 0;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                nums[k] = aux[j++];
            }
            else if (j > hi) {
                nums[k] = aux[i++];
            }
            else if (aux[i] <= aux[j]) {
                nums[k] = aux[i++];
            }
            else {
                count += mid + 1 - i;
                nums[k] = aux[j++];
            }
        }
        return count;
    }

    /**
     * LC376. 摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。
     * 少于两个元素的序列也是摆动序列。
     *
     *  背包解法
     * public int wiggleMaxLength(int[] nums) {
     *     int n = nums.length;
     *     if (n < 2) {
     *         return n;
     *     }
     *     int up = 1, down = 1;
     *     for (int i = 1; i < n; i++) {
     *         if (nums[i] > nums[i - 1]) {
     *             up = Math.max(up, down + 1);
     *         } else if (nums[i] < nums[i - 1]) {
     *             down = Math.max(up + 1, down);
     *         }
     *     }
     *     return Math.max(up, down);
     * }
     * @param nums 一个数组
     * @return 返回是摆动数组的最长子序列的长度
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     *  Map解法：
     *         Map<Integer, Integer> map = new HashMap<>();
     *         for (int num: nums) {
     *             map.put(num, map.getOrDefault(num, 0) + 1);
     *         }
     *         for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
     *             if (entry.getValue() > nums.length >> 1) {
     *                 return entry.getKey();
     *             }
     *         }
     *         throw new IllegalArgumentException();
     * @param nums 查找的数组
     * @return 超过数组长度一半的数字
     */
    public int majorityElement(int[] nums) {
        //求众数: 摩尔投票法
        int target = nums[0], count = 0;
        for (int num: nums) {
            if (count == 0) target = num;
            count += target == num ? 1 : -1;
        }
        return target;
    }

    /**
     * 根据身高排列数组
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            else {
                return o2[1] - o1[1];
            }
        });

        int[][] ans = new int[people.length][];

        for (int[] person : people) {
            int space = person[1] + 1;
            for (int j = 0; j < people.length; j++) {
                if (ans[j] == null) {
                    space--;
                    if (space == 0) {
                        ans[j] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 排序数组
     */
    public int[] sortArray(int[] nums) {
        sortArrayHandle(0, nums.length-1, nums);
        return nums;
    }

    private void sortArrayHandle(int lo, int hi, int[] nums) {
        if (hi <= lo) return;
        int i = lo, j = hi+1;
        int standard = nums[lo];
        while (true) {
            while (nums[++i] < standard) if (i == hi) break;
            while (nums[--j] > standard) if (j == lo) break;
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        sortArrayHandle(lo, j - 1, nums);
        sortArrayHandle(j + 1, hi, nums);
    }

    void swap(int[] nums, int x, int y) {
        if (x == y) return;
        nums[x] ^= nums[y];
        nums[y] ^= nums[x];
        nums[x] ^= nums[y];
    }


    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: arr1) {
            Integer result = map.getOrDefault(i, 0);
            map.put(i, result+1);
        }

        int count = 0;
        for (int i: arr2) {
            Integer integer = map.getOrDefault(i, 0);
            while (integer-- != 0) {
                arr1[count++] = i;
            }
            map.remove(i);
        }
        Integer[] sets = (Integer[]) map.keySet().toArray();
        Arrays.sort(sets);
        for (int i: sets) {
            arr1[count++] = i;
        }

        return arr1;
    }

    /**
     * 下一个排列
     *
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;
        for (int index = nums.length-2; index >= 0; --index) {
            if (nums[index] < nums[index+1]) {
                for (int j=nums.length-1; j > index; --j) {
                    if (nums[index] < nums[j]) {
                        swap(nums, index, j);
                        reverse(nums, index+1);
                        return;
                    }
                }
            }
            else {
                if (index == 0) {
                    reverse(nums, 0);
                    return;
                }
            }
        }
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }



    /**
     * 插入区间
     * @param intervals 原来的区间，二维数组intervals[left][right]。每一维有两个数字
     * @param newInterval 插入的区间
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //记录插入区间的左右端点
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    //将区间直接插入,插入区间只插入一次
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                //将无交集的区间直接插入
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    /**
     * 判断一个山脉数组
     */
    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // 递增扫描
        while (i + 1 < N && A[i] < A[i + 1]) {
            i++;
        }

        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;
    }

    /**
     * 求两个数组的并集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i: nums1) {
            set1.add(i);
        }

        for (int i: nums2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }
        int[] answer = new int[set2.size()];
        int index = 0;
        for (int i: set2) {
            answer[index++] = i;
        }
        return answer;
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occur = new HashMap<>();
        for (int x : arr) {
            occur.put(x, occur.getOrDefault(x, 0) + 1);
        }
        Set<Integer> times = new HashSet<>();
        for (Map.Entry<Integer, Integer> x : occur.entrySet()) {
            times.add(x.getValue());
        }
        return times.size() == occur.size();
    }

    int mod = (int) (1e9 + 7);

    /**
     * 计数排序记录小于当前数字的数字
     * 给你一个数组nums，对于其中每个元素nums[i]，请你统计数组中比它小的所有数字的数目。
     * 换而言之，对于每个nums[i]你必须计算出有效的j的数量，其中 j 满足j != i 且 nums[j] < nums[i]
     * 以数组形式返回答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
     *
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] answers = new int[101];

        for (int num : nums) {
            answers[num]++;
        }

        int carry = answers[0];
        answers[0] = 0;
        for (int i = 0; i < answers.length - 1; ++i) {
            int temp = answers[i + 1];
            answers[i + 1] = answers[i] + carry;
            carry = temp;
        }

        for (int i = 0; i < nums.length; ++i) {
            nums[i] = answers[nums[i]];
        }
        return nums;
    }

    public int fib(int n) {
        int[] a = new int[101];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i <= n; ++i) {
            a[i] = (a[i - 1] * a[i]) % mod;
        }
        return a[n];
    }

    /**
     * 贪心算法解决最少子区间合并大区间问题
     * 使用maxn数组，记录下标为n的数组到达的最远长度
     */
    public int videoStitching(int[][] clips, int T) {
        int[] maxn = new int[T];
        int last = 0, ret = 0, pre = 0;
        for (int[] clip : clips) {
            if (clip[0] < T) {
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
            }
        }
        for (int i = 0; i < T; i++) {
            last = Math.max(last, maxn[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                ret++;
                pre = last;
            }
        }
        return ret;
    }

    public List<String> commonChars(String[] A) {
        int[][] letter = new int[A.length][26];
        List<String> list = new ArrayList<>();

        for (int i = 0; i < A.length; ++i) {
            for (char a : A[i].toCharArray()) {
                letter[i][Character.toLowerCase(a) - 'a']++;
            }
        }

        for (int i = 0; i < 26; ++i) {
            int min = 0x3f3f3f3f;
            for (int[] ints : letter) {
                min = Math.min(ints[i], min);
                if (min < 0) {
                    break;
                }
            }
            for (int j = 0; j < min; ++j) {
                char a = (char) ('a' + i);
                list.add(String.valueOf(a));
            }
        }
        return list;
    }

    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }
}
