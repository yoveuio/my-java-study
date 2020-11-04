package org.example.homework.communication;

/**
 * @ClassName Add
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/22 15:05
 * @Version 1.0
 */
public class Add {
    private String lock;

    public Add(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}