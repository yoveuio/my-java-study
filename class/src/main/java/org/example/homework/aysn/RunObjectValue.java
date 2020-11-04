package org.example.homework.aysn;

/**
 * @ClassName RunObjectValue
 * @Description 即时改变对象的值，对象的锁也是相同的
 * @Author yoveuio
 * @Date 2020/10/15 16:52
 * @Version 1.0
 */
public class RunObjectValue {

    static class ChangeValue implements Runnable{
        private volatile int value = 0;

        public void setValue(int value){
            this.value = value;
        }

        @Override
        public void run() {
            synchronized (this){
                try {
                    for (int i = 0; i < 20; ++i) {
                        System.out.println("Thread:" + Thread.currentThread().getName()
                                + "--Value = " + value);
                        Thread.sleep(100);
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeValue changeValue = new ChangeValue();
        new Thread(changeValue, "A").start();
        Thread.sleep(100);
        changeValue.setValue(1);
        new Thread(changeValue, "B").start();
    }
}

