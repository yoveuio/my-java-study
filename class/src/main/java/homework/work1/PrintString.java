package homework.work1;

/**
 * @ClassName PrintString
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/10 11:13
 * @Version 1.0
 */
public class PrintString implements Runnable{

    private volatile boolean isContinuePrint= true;
    public boolean isContinuePrint(){
        return this.isContinuePrint;
    }

    public void setContinuePrint(boolean isContinuePrint){
        this.isContinuePrint=isContinuePrint;
    }

    @Override
    public void run() {
        try {
            while (isContinuePrint) {
                System.out.println("run printStringMethod threadName="
                        + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
