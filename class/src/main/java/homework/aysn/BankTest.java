package homework.aysn;

import java.util.concurrent.Callable;

/**
 * @ClassName BankTest
 * @Description 测试Bank类的同步
 *  两个线程，一个线程调用synchronized修饰方法，另一个线程可以调用非synchronized修饰的方法，互不影响
 *  两个线程，一个线程执行synchronized代码块，另一个线程执行非synchronized代码块，互不影响
 *
 * @Author yoveuio
 * @Date 2020/10/13 10:18
 * @Version 1.0
 */
public class BankTest {
    public static Bank bank = new Bank(100);

    public static void deposit(int money) {
        try {
            for (int i = 0; i < 10; ++i) {
                bank.deposit(money);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> deposit(10));
        Thread threadB = new Thread(() -> deposit(10));

        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bank.getAccount());
    }
}
