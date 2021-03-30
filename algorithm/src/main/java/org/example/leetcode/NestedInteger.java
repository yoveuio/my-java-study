package org.example.leetcode;

import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className NestedInteger
 * @description
 * This is the interface that allows for creating nested lists.
 * You should not implement it, or speculate about its implementation
 * @date 2021/3/23 9:28
 */
public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}