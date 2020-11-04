package org.example.homework.threadpool;

import java.util.concurrent.*;

/**
 * @ClassName Bank
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/27 19:07
 * @Version 1.0
 */
public class Bank {
    private static ThreadLocal<Integer> account = ThreadLocal.withInitial(() -> 100);

    public synchronized void deposit(int money) {
        account.set(account.get() + money);
    }

    public int getAccount() {
        return account.get();
    }

    static class Task implements Callable<Integer> {
        Bank bank;

        public Task(Bank bank) {
            this.bank = bank;
        }

        @Override
        public Integer call() throws Exception {
            for (int i=0; i<10; ++i) {
                bank.deposit(10);
            }
            return bank.getAccount();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Bank bank = new Bank();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> futureA = executorService.submit(new Task(bank));
        Future<Integer> futureB = executorService.submit(new Task(bank));
        executorService.shutdown();
        System.out.println("A:" + futureA.get() + "\nB:" + futureB.get());
    }
}
