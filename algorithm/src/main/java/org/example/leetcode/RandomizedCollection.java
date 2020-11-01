package org.example.leetcode;

import java.util.*;

/**
 * @ClassName RandomizedCollection
 * @Description
 * 设计一个支持在平均时间复杂度O(1)下，执行以下操作的数据结构。
 *
 * 注意: 允许出现重复元素。
 *
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 * @Author yoveuio
 * @Date 2020/10/31 10:45
 * @Version 1.0
 */
class RandomizedCollection {
    //维护下标在集合中出现的每一个下标集合
    Map<Integer, Set<Integer>> idx;
    //将每个可以重复的数值存储在nums集合中,随机生成一个下标索引即可实现插入和查找的O(1)时间复杂度的实现
    List<Integer> nums;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        idx = new HashMap<Integer, Set<Integer>>();
        nums = new ArrayList<Integer>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
        set.add(nums.size() - 1);
        idx.put(val, set);
        return set.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!idx.containsKey(val)) {
            return false;
        }
        Iterator<Integer> it = idx.get(val).iterator();
        int i = it.next();
        //将最后一位移到删除的那个地方，即将原位置的元素删除
        int lastNum = nums.get(nums.size() - 1);
        nums.set(i, lastNum);
        //维护索引表，将原来的索引删除
        idx.get(val).remove(i);
        idx.get(lastNum).remove(nums.size() - 1);
        //如果删除的是最后一个元素，就不需要将最后一个元素加入索引节点
        if (i < nums.size() - 1) {
            idx.get(lastNum).add(i);
        }
        //如果元素全部删除，就将其从索引表中移除
        if (idx.get(val).size() == 0) {
            idx.remove(val);
        }
        //删除元素
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get((int) (Math.random() * nums.size()));
    }
}

