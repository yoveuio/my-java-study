package org.example.lock.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author yoveuio
 * @version 1.0
 * @className AQS
 * @description AQS解析
 * @date 2021/2/13 15:16
 */
public class AQS {
    AbstractQueuedSynchronizer synchronizer;

    static final class Node {
        /**
         * 当前节点是共享的
         */
        static final Node SHARED = new Node();
        /**
         * 当前节点是排他的
         */
        static final Node EXCLUSIVE = null;

        /**
         * waitStatus值，指示线程已取消
         */
        static final int CANCELLED = 1;
        /**
         * waitStatus值，指示后续线程需要释放
         */
        static final int SIGNAL = -1;
        /**
         * waitStatus值，指示线程条件条件队列
         */
        static final int CONDITION = -2;
        /**
         * waitStatus值，指示下一个acquireShared应该无条件传播
         */
        static final int PROPAGATE = -3;

        volatile int waitStatus;

        /**
         * 前一个节点
         */
        volatile Node prev;

        /**
         * 后一个节点
         */
        volatile Node next;

        /**
         * 拥有当前节点的线程
         */
        volatile Thread thread;

        /**
         * 对于同步队列，代表着共享还是排它节点
         * 对于条件队列，代表下一个节点
         */
        Node nextWaiter;
    }
}
