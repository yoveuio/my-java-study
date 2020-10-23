package homework.communication;

/**
 * @ClassName ThreadAdd
 * @Description 添加的线程
 * @Author yoveuio
 * @Date 2020/10/22 15:06
 * @Version 1.0
 */
public class ThreadAdd extends Thread {
    private Add p;

    public ThreadAdd(Add p) {
        this.p = p;
    }

    public void run() {
        p.add();
        p.add();
    }

} //删除list中的一个元素的线程

