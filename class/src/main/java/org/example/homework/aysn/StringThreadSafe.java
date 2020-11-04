package org.example.homework.aysn;

/**
 * @ClassName StringThreadSafe
 * @Description String、StringBuilder和StringBuffer
 * @Author yoveuio
 * @Date 2020/10/15 11:07
 * @Version 1.0
 */
public class StringThreadSafe {
    public static StringBuffer allInfo = new StringBuffer();
    public static void main(String[] args) {
        System.out.println("线程名称为：" + Thread.currentThread().getName()
                + " 在" + System.currentTimeMillis() + "进入");
        // 追加含五个字符的apple
        Thread stringThread01 = new Thread(new AppendStringRunnable("Apple"));
        // 追加含三个字符的boy
        Thread stringThread02 = new Thread(new AppendStringRunnable("boy"));
        // 追加含三个字符的cat
        Thread stringThread03 = new Thread(new AppendStringRunnable("cat"));
        // 追加含四个字符的duck
        Thread stringThread04 = new Thread(new AppendStringRunnable("duck"));
        stringThread01.start();
        stringThread02.start();
        stringThread03.start();
        stringThread04.start();
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        //输出到底有多少字符的信息量被追加到allInfo变量
        System.out.println(allInfo.length());
        System.out.println("线程名称为：" + Thread.currentThread().getName()
                + " 在" + System.currentTimeMillis() + "离开");
    }

}

class AppendStringRunnable implements Runnable{
    private String info="";

    AppendStringRunnable(String info){
        this.info = info;
    }
    public void run(){
        for(int i =0; i<100; i++){
            //String: 17974-12967 867
            //StringBuilder: 7398-2398 1500
            //StringBuffer: 7887-2886 1500
            StringThreadSafe.allInfo.append(info);
        }
    }
}


