package org.example;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
class Solution {

    Map<Character, Boolean> map = new LinkedHashMap<>();

    public void Insert(char ch)
    {
        map.put(ch, map.containsKey(ch));
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        Set<Map.Entry<Character, Boolean>> entries = map.entrySet();
        for (Map.Entry<Character, Boolean> entry: entries) {
            if (!entry.getValue()) return entry.getKey();
        }
        throw new ValueException("error");
    }
}
