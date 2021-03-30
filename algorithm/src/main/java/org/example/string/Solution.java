package org.example.string;


import java.util.*;

/**
 * @ClassName Solution
 * @Description 字符串有关算法
 * @Author yoveuio
 * @Date 2020/10/21 19:03
 * @Version 1.0
 */
@SuppressWarnings("unused")
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 58. 最后一个单词的长度
     */
    @SuppressWarnings("all")
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int n = s.length();
        int i = n - 1;
        while (--i >= 0 && s.charAt(i) != ' ');
        return n - i;
    }

    /**
     * LC830. 较大分组的位置
     * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
     * 例如，在字符串 s = "abbxxxxzyy"中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
     * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
     * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
     * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/positions-of-large-groups
     * @param s 一个字符串
     * @return 返回其中连续字符大于等于三个的区间
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        int n = s.length();
        List<List<Integer>> answers = new ArrayList<>();
        int prev = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || s.charAt(i) != s.charAt(prev)) {
                if (i - prev >= 3) {
                    List<Integer> answer = new ArrayList<>();
                    answer.add(prev); answer.add(i - 1);
                    answers.add(answer);
                }
                prev = i;
            }
        }
        return answers;
    }

    /**
     * LC1704. 判断字符串的两半是否相似
     * 给你一个偶数长度的字符串 s 。将其拆分成长度相同的两半，前一半为 a ，后一半为 b 。
     * 两个字符串 相似 的前提是它们都含有相同数目的元音（'a'，'e'，'i'，'o'，'u'，'A'，'E'，'I'，'O'，'U'）。注意，s 可能同时含有大写和小写字母。
     * 如果 a 和 b 相似，返回 true ；否则，返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/determine-if-string-halves-are-alike
     */
    public boolean halvesAreAlike(String s) {
        final Set<Character> set = new HashSet<Character>(){{
            add('a'); add('e'); add('i'); add('o'); add('u');
            add('A'); add('E'); add('I'); add('O'); add('U');
        }};
        int countA = 0, countB = 0;
        int n = s.length();
        for (int i = 0; i < n >> 1; i++) {
            if (set.contains(s.charAt(i))) countA++;
            if (set.contains(s.charAt(n - i - 1))) countB++;
        }
        return countA == countB;
    }

    /**
     * LC205. 同构字符串
     * 给定两个字符串s和t，判断它们是否是同构的。
     *
     * 如果s中的字符可以被替换得到t，那么这两个字符串是同构的。
     *
     * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/isomorphic-strings
     * @return 返回s和t是否重构
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        int n = s.length();
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            char ct = t.charAt(i), cs = s.charAt(i);
            if (!map.containsKey(ct)) {
                map.put(ct, cs);
                if (set.contains(cs)) return false;
                set.add(cs);
                continue;
            }
            if (map.get(ct) != cs) {
                return false;
            }
        }
        return true;
    }

    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     *
     * @param s 字符串
     * @return 返回最长子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }

    public String reverseLeftWords(String s, int n) {
        if (s == null || n < 0 || s.length() < n) return s;
        return s.substring(n) + s.substring(0, n);
    }

    /**
     * LC389. 找不同
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     * <p>
     * https://leetcode-cn.com/problems/find-the-difference/submissions/
     */
    public char findTheDifference(String s, String t) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return (char) (res ^ t.charAt(s.length()));
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
     */
    public String reverseWords(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s, i + 1, j + 1).append(" "); // 添加单词
            while (i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    private void swap(String[] s, int i, int j) {
        String temp = s[i];
        s[j] = s[i];
        s[i] = temp;
    }

    /**
     * 给定一种规律 pattern和一个字符串str，判断 str 是否遵循相同的规律。
     * 这里的遵循指完全匹配，例如，pattern里的每个字母和字符串str中的每个非空单词之间存在着双向连接的对应规律。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-pattern
     *
     * @param pattern 匹配模式
     * @param s       字符串
     * @return 返回字符串是否匹配模式
     */
    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        Map<Character, String> charMap = new HashMap<>();
        Map<String, Character> stringMap = new HashMap<>();
        if (strings.length < pattern.length()) return false;
        for (int i = 0; i < strings.length; i++) {
            String s1 = strings[i];
            char c = pattern.charAt(i);
            if ((charMap.containsKey(c) && !Objects.equals(charMap.get(c), s1)) ||
                    (stringMap.containsKey(s1) && stringMap.get(s1) != c)) {
                return false;
            }
            charMap.put(c, s1);
            stringMap.put(s1, c);
        }
        return true;
    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 计数排序
     *
     * @param strs 一个字符串数组，里面有0~n个字母异位词
     * @return 返回字母异位词分组后的结果
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> answers = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] counts = new int[26];
            for (char ch : s.toCharArray()) {
                counts[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) (i + 'a'));
                    sb.append(counts[i]);
                }
            }
            List<String> answer = map.getOrDefault(sb.toString(), new ArrayList<>());
            answer.add(s);
            map.put(sb.toString(), answer);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            answers.add(entry.getValue());
        }
        return answers;
    }

    /**
     * 找到第一个只出现一次的数
     *
     * @param s 一个包含很多字符的数
     * @return 第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        Map<Character, Integer> answer = new HashMap<>();
        for (char c : s.toCharArray()) {
            answer.put(c, answer.getOrDefault(c, 0) + 1);
        }

        for (char c : s.toCharArray()) {
            if (answer.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * LC387. 字符串中的第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
    public int firstUniqChar2(String s) {
        if (s == null || s.length() == 0) return -1;
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            map.put(c, map.containsKey(c) ? Integer.MAX_VALUE:i);
        }

        int answer = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            answer = Math.min(entry.getValue(), answer);
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    /**
     * 打印出字符串中字符所有的排列，不能有重复元素
     */
    List<String> answer = new LinkedList<>();
    HashMap<Character, Integer> mapS = new HashMap<>();

    public String[] permutation(String s) {
        for (char c : s.toCharArray()) {
            mapS.put(c, mapS.getOrDefault(c, 0) + 1);
        }
        permutationHandler("", s.length());
        return answer.toArray(new String[0]);
    }

    private void permutationHandler(String s, int size) {
        if (s.length() == size) {
            answer.add(s);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(s);
        Set<Character> characters = mapS.keySet();
        for (char c : characters) {
            int count = mapS.get(c);
            if (count <= 0) continue;
            mapS.put(c, count - 1);
            stringBuilder.append(c);
            permutationHandler(stringBuilder.toString(), size);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            mapS.put(c, count);
        }
    }

    /**
     * 给定字符串s、t，判断t是否是s的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        int[] letterS = new int[26];
        int[] letterT = new int[26];
        final int a = 'a';
        for (int i = 0; i < s.length(); i++) {
            letterS[s.charAt(i) - a]++;
            letterT[t.charAt(i) - a]++;
        }

        for (int i = 0; i < 26; i++) {
            if (letterS[i] != letterT[i]) return false;
        }
        return true;
    }

    /**
     * 将一个字符串尽可能的分割，保证每个字母只会出现在一个片段中
     * 解法：贪心+双指针
     * 记录开始位置和结束位置。保证片段中的每一对start-end都在范围内即可
     *
     * @param S 被分割的字符串
     * @return 返回分割后的下标
     */
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    /**
     * 把字符串s中的每个空格替换成"%20"
     *
     * @param s 字符串
     * @return 处理后的字符串
     */
    public String replaceSpace(String s) {
        StringBuilder stringBuffer = new StringBuilder();
        for (char c : s.toCharArray()) {
            stringBuffer.append(c == ' ' ? "%20" : c);
        }
        return stringBuffer.toString();
    }

    /**
     * 名字匹配，使用双指针
     *
     * @param name  名字
     * @param typed 匹配的名字
     * @return 匹配成功返回true
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
