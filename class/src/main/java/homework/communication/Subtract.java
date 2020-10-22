package homework.communication;

/**
 * @ClassName Subtract
 * @Description 删除的类
 * @Author yoveuio
 * @Date 2020/10/22 15:06
 * @Version 1.0
 */
public class Subtract {
    private String lock;

    public Subtract(String lock) {
        this.lock = lock;
    }

    public void subtract() {
        try {
            synchronized (lock) {
                while (ValueObject.list.size() == 0) {
                    lock.wait();
                }
                ValueObject.list.remove(0);
                System.out.println("list size=" + ValueObject.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
