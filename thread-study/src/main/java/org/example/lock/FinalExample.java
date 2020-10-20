package org.example.lock;

/**
 * @ClassName FinalExample
 * @Description final域的重排序规则
 * @Author yoveuio
 * @Date 2020/10/19 21:50
 * @Version 1.0
 */
public class FinalExample {

    int i;
    final int j;
    static FinalExample finalExample;

    public FinalExample(){
        //下面两条语句不能重排序
        i = 1;
        j = 2;
    }

    public static void writer() {
        finalExample = new FinalExample();
    }

    public static void reader() {
        FinalExample object = finalExample;
        System.out.println(object.i + " " + object.j);
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(FinalExample::writer);
        Thread threadB = new Thread(FinalExample::reader);
        threadA.start();
        threadB.start();
    }
}
