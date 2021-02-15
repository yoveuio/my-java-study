package org.example.lock.reentrant_lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yoveuio
 * @version 1.0
 * @className LockTest
 * @description ReentrantLock本身是不继承AQS的，实现了Lock接口
 * @date 2021/2/15 12:48
 */
public class LockTest {
    ReentrantLock lock = new ReentrantLock();
}
