package org.example.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 队列题目
 * @date 2020/12/11 9:02
 */
public class Solution {

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
     *
     * @param n n个数字
     * @param m 删除第m个数字
     * @return 返回最后的结果
     */
    public int lastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; ++i) {
            f = (m + f) % i;
        }
        return f;
    }

    /**
     * Dota2 参议院
     * Dota2 的世界里有两个阵营：Radiant(天辉)和Dire(夜魇)
     * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
     * 禁止一名参议员的权利：
     * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
     * 宣布胜利：
     * 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/dota2-senate
     *
     * @param senate 一个字符串，共有n位代表n个议员，其中R代表天辉，D代表夜魇
     * @return 如果是是天辉胜利就输出Radiant，如果夜魇胜利就输出Dire
     */
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }

        int n = senate.length();
        while (!dire.isEmpty() && !radiant.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.add(radiantIndex + n);
            } else {
                dire.add(direIndex + n);
            }
        }
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}
