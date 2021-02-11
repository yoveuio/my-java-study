package org.example;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yoveuio
 * @version 1.0
 * @className Solution
 * @description 做题
 * @date 2021/1/9 9:20
 */
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[]{5,2,3,4,1,6,7,0,8};
        for (int j : a) {
            solution.Insert(j);
            System.out.println(solution.GetMedian());
        }
    }

    Queue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);
    Queue<Integer> min = new PriorityQueue<>();

    public void Insert(Integer num) {
        if (max.size() != min.size()) {
            max.offer(num);
            min.offer(max.poll());
        }
        else {
            min.offer(num);
            max.offer(min.poll());
        }
    }

    public Double GetMedian() {
        if (max.size() == 0) return 0.0;
        if (max.size() == min.size()) return (max.peek() + min.peek()) / 2.0;
        else return 1.0 * max.peek();
    }
}
