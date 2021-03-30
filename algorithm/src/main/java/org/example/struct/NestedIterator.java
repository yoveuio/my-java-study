package org.example.struct;

import org.example.leetcode.NestedInteger;

import java.util.*;

/**
 * @author yoveuio
 * @version 1.0
 * @className NestedIterator
 * @description 341. 扁平化嵌套列表迭代器
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
 * @date 2021/3/23 9:26
 */
@SuppressWarnings("unused")
public class NestedIterator implements Iterator<Integer> {
    Deque<Integer> integerList;
    int size;
    int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.size = nestedList.size();
        integerList = new ArrayDeque<>();

        getAllInteger(nestedList);
    }

    private void getAllInteger(List<NestedInteger> nestedList) {
        if (nestedList == null) return ;
        int n = nestedList.size();
        for (NestedInteger nestedInteger: nestedList) {
            if (nestedInteger.isInteger()) {
                integerList.add(nestedInteger.getInteger());
            } else {
                getAllInteger(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return integerList.poll();
    }

    @Override
    public boolean hasNext() {
        return !integerList.isEmpty();
    }
}
