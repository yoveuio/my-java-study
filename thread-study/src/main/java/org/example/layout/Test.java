package org.example.layout;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ClassLayout
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/6/23 11:37
 * @Version 1.0
 */
public class Test {

    Executors executor = null;
    ThreadPoolExecutor threadPoolExecutor = null;


    public void test(){

    }

    public static void main(String[] args) {

        ReentrantLock reentrantLock = null;
        Sync sync = null;
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = null;
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
