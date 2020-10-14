package homework.aysn;

/**
 * @ClassName AyncTest
 * @Description 同步是不能被继承的，只有显示写上synchronized关键字的方法才是同步方法
 * @Author yoveuio
 * @Date 2020/10/13 16:49
 * @Version 1.0
 */
public class SyncTest implements Runnable{

    static B b = new B();

    @Override
    public void run() {
        for (int i=0; i<50; ++i) {
            b.test();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class A{
        int a = 0;

        public synchronized void test() {
            a+=1;
        }
    }

    static class B extends A{
        int a = 0;
        @Override
        public void test(){
            a+=1;
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        SyncTest testA = new SyncTest();
        SyncTest testB = new SyncTest();

        new Thread(testA).start();
        new Thread(testB).start();
    }
}
