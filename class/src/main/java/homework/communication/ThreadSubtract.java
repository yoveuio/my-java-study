package homework.communication;

/**
 * @ClassName ThreadSubtract
 * @Description T4删除的线程
 * @Author yoveuio
 * @Date 2020/10/22 15:08
 * @Version 1.0
 */
public class ThreadSubtract extends Thread{ private Subtract c;

    public ThreadSubtract(Subtract c) {
        this.c = c;
    }

    public void run() {
        c.subtract();
    }
}