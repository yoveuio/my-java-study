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
        Map<Integer, Integer> occur = new HashMap<Integer, Integer>();
        for (int x : arr) {
            occur.put(x, occur.getOrDefault(x, 0) + 1);
        }
        Set<Integer> times = new HashSet<Integer>();
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
     * @param nums
     * @return
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
     *
     * @param clips
     * @param T
     * @return
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
