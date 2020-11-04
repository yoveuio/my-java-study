package org.example.homework.aysn;

/**
 * @ClassName Bank
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/13 10:14
 * @Version 1.0
 */
public class Bank {
    private volatile int account;

    public Bank(int account) {
        this.account = account;
    }

    /**
     * 向账户存入money元
     * @param money 存入的钱
     */
    public synchronized void deposit(int money) {
        if (money < 0) money = 0;
        account += money;
        System.out.println(Thread.currentThread().getName() + "  账户的余额是：" + getAccount());
    }

    /**
     * 查询账户余额
     * @return account
     */
    public int getAccount() {
        return account;
    }
}