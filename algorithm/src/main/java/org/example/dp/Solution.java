package org.example.dp;

import java.util.*;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/26 8:47
 * @Version 1.0
 */
public class Solution {
    /**
     * 青蛙跳台阶
     * @param n
     * @return
     */
    public int numWays(int n) {
        int a = 1, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return a;
    }
}
