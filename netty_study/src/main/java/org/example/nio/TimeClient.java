package org.example.nio;

/**
 * @ClassName TimeClient
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/23 21:44
 * @Version 1.0
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        new Thread(new TimeClientHandel("127.0.0.1", port), "TimeClient-001").start();
    }
}
