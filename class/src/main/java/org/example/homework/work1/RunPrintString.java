package org.example.homework.work1;

/**
 * @ClassName RunPrintString
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/10 11:15
 * @Version 1.0
 */
public class RunPrintString {
    public static void main(String[] args)throws Exception {
        PrintString printStringService = new PrintString();
        new Thread(printStringService).start();
        Thread.sleep(2000);
        System.out.println("我要停止它！stopThread="
                + Thread.currentThread().getName());
        printStringService.setContinuePrint(false);
    }
}
