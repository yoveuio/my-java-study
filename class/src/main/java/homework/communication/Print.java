package homework.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Print
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/22 15:32
 * @Version 1.0
 */
public class Print {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    boolean flag = false;

    void printOdd() {
        lock.lock();
        try {
            for (int i = 1; i <= 50; ++i) {
                if (i % 2 != 0) {
                    System.out.println(i);
                }
            }
            condition.signalAll();
            flag = true;
        } finally {
            lock.unlock();
        }
    }

    void printEven() {
        lock.lock();
        try{
            while(!flag){
                condition.awaitUninterruptibly();
            }
            for (int i=1; i<=50; ++i) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
            flag = false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Print print = new Print();
        new Thread(print::printOdd).start();
        new Thread(print::printEven).start();

        Thread.sleep(3000);
        new Thread(print::printEven).start();
        new Thread(print::printOdd).start();
    }
}
