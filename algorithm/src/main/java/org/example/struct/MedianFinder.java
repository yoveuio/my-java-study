package org.example.struct;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName MedianFinder
 * @Description 数据流中的中位数
 * 用大顶堆保存较小的一半，小顶堆保存较大的一半。中间既是中位数
 * @Author yoveuio
 * @Date 2020/11/28 10:27
 * @Version 1.0
 */
public class MedianFinder {
    Queue<Integer> queueA;
    Queue<Integer> queueB;

    /** initialize your data structure here. */
    public MedianFinder() {
        //大顶堆，保存小的一半
        queueA = new PriorityQueue<>((i1, i2) -> i2 - i1);
        //小顶堆，保存大的一半
        queueB = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (queueA.size() != queueB.size()) {
            queueA.add(num);
            queueB.add(queueA.poll());
        }
        else {
            queueB.add(num);
            queueA.add(queueA.poll());
        }
    }

    public double findMedian() {
        if (queueA.size() == 0) return 0.0;
        return queueA.size() != queueB.size() ? queueA.peek() : (queueB.peek() - queueA.peek()) / 2.0 + queueA.peek();
    }
}
