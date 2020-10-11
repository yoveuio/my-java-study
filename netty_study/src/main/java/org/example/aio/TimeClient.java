package org.example.aio;

/**
 * @ClassName TImeClient
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/11 20:33
 * @Version 1.0
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        String host = "127.0.0.1";
        new Thread(new AsyncTimeClientHandler(host, port), "AIO-AsyncTimeClientHandler").start();
    }

}
