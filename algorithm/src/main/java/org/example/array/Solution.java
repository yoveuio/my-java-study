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

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    void swap(int[] nums, int x, int y) {
        if (x == y) return;
        nums[x] ^= nums[y];
        nums[y] ^= nums[x];
        nums[x] ^= nums[y];
    }
    /* ------------------------------------------------分割线--------------------------------------------------- */

    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 解法一：数组旋转
     *  将需要旋转的数组分三次翻转可以得到旋转后的数组
     *
     * 解法二：数字替换
     *  使用temp存储被替换的元素，为他们找到合适的位置
     *  总共需要gcd(k, n)次。gcd为最大公约数
     *  class Solution {
     *     public void rotate(int[] nums, int k) {
     *         int n = nums.length;
     *         k = k % n;
     *         int count = gcd(k, n);
     *         for (int start = 0; start < count; ++start) {
     *             int current = start;
     *             int prev = nums[start];
     *             do {
     *                 int next = (current + k) % n;
     *                 int temp = nums[next];
     *                 nums[next] = prev;
     *                 prev = temp;
     *                 current = next;
     *             } while (start != current);
     *         }
     *     }
     *
     *     public int gcd(int x, int y) {
     *         return y > 0 ? gcd(y, x % y) : x;
     *     }
     * }
     *
     * 链接：https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
     * 来源：力扣（LeetCode）
     * @param nums 数组
     * @param k 要求旋转的位置
     */
    public void rotate(int[] nums, int k) {
        // 如果旋转次数超过数组的长度。每旋转一个数组的长度数组就会变回来
        k %= nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r++] = temp;
        }
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
     *
     * @param nums 数组长度为5。数组的数取值为 [0, 13]
     * @return 返回nums是不是顺子
     */
    public boolean isStraight(int[] nums) {
        int joker = 0;
        Arrays.sort(nums); // 数组排序
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) joker++; // 统计大小王数量
            else if (nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
        }
        return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
     * 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        if (a == null || a.length < 1) return new int[]{};
        int n = a.length;
        int[] b = new int[n];
        Arrays.fill(b, 1);
        int left = 1, right = 1;
        for (int i = 1; i < n; i++) {
            left *= a[i];
            b[i] *= left;
            right *= a[n - i - 2];
            b[n - i - 2] *= right;
        }
        return b;
    }

    /**
     * LC455. 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。
     * 如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/assign-cookies
     *
     * @param g 小孩的胃口值
     * @param s 能分配的饼干
     * @return 返回能满足的孩子的数量
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int n = Math.min(g.length, s.length);
        int ans = 0, sCount = -1;
        for (int i = 0; i < n; i++) {
            while (++sCount < s.length && s[sCount] < g[i]) ;
            if (sCount == s.length) break;
            ans++;
        }
        return ans;
    }

    /**
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
     *
     * @param target 目标值
     * @return 返回所有符合要求的连续子序列
     */
    public int[][] findContinuousSequence(int target) {
        int l = 1, r = 2;
        List<int[]> answers = new ArrayList<>();
        while (l < r) {
            int sum = (l + r) * (r - l + 1) >> 1;
            if (sum == target) {
                int[] answer = new int[r - l + 1];
                for (int i = 0; i < r - l + 1; i++) {
                    answer[i] = l + i;
                }
                answers.add(answer);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return answers.toArray(new int[answers.size()][]);
    }

    /**
     * 和为target的两个数字
     */
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int sum = nums[l] + nums[r];
            if (sum == target) {
                return new int[]{nums[l], nums[r]};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 判断是否有重复元素
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }

    /**
     * 剑指51 逆序数组
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     * @param nums 一个数组，数组长度：0-50000
     * @return 逆序对的数量
     */
    public int reversePairs(int[] nums) {
        if (nums.length < 2) return 0;
        aux = new int[nums.length];
        return reversePairsHandler(nums, 0, nums.length - 1);
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
            } else if (j > hi) {
                nums[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                nums[k] = aux[i++];
            } else {
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
     * <p>
     * 背包解法
     * public int wiggleMaxLength(int[] nums) {
     * int n = nums.length;
     * if (n < 2) {
     * return n;
     * }
     * int up = 1, down = 1;
     * for (int i = 1; i < n; i++) {
     * if (nums[i] > nums[i - 1]) {
     * up = Math.max(up, down + 1);
     * } else if (nums[i] < nums[i - 1]) {
     * down = Math.max(up + 1, down);
     * }
     * }
     * return Math.max(up, down);
     * }
     *
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
     * Map解法：
     * Map<Integer, Integer> map = new HashMap<>();
     * for (int num: nums) {
     * map.put(num, map.getOrDefault(num, 0) + 1);
     * }
     * for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
     * if (entry.getValue() > nums.length >> 1) {
     * return entry.getKey();
     * }
     * }
     * throw new IllegalArgumentException();
     *
     * @param nums 查找的数组
     * @return 超过数组长度一半的数字
     */
    public int majorityElement(int[] nums) {
        //求众数: 摩尔投票法
        int target = nums[0], count = 0;
        for (int num : nums) {
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
            } else {
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
        sortArrayHandle(0, nums.length - 1, nums);
        return nums;
    }

    private void sortArrayHandle(int lo, int hi, int[] nums) {
        if (hi <= lo) return;
        int i = lo, j = hi + 1;
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

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr1) {
            Integer result = map.getOrDefault(i, 0);
            map.put(i, result + 1);
        }

        int count = 0;
        for (int i : arr2) {
            Integer integer = map.getOrDefault(i, 0);
            while (integer-- != 0) {
                arr1[count++] = i;
            }
            map.remove(i);
        }
        Integer[] sets = (Integer[]) map.keySet().toArray();
        Arrays.sort(sets);
        for (int i : sets) {
            arr1[count++] = i;
        }

        return arr1;
    }

    /**
     * 下一个排列
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;
        for (int index = nums.length - 2; index >= 0; --index) {
            if (nums[index] < nums[index + 1]) {
                for (int j = nums.length - 1; j > index; --j) {
                    if (nums[index] < nums[j]) {
                        swap(nums, index, j);
                        reverse(nums, index + 1);
                        return;
                    }
                }
            } else {
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
     *
     * @param intervals   原来的区间，二维数组intervals[left][right]。每一维有两个数字
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

        for (int i : nums1) {
            set1.add(i);
        }

        for (int i : nums2) {
            if (set1.contains(i)) {
                set2.add(i);
            }
        }
        int[] answer = new int[set2.size()];
        int index = 0;
        for (int i : set2) {
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
