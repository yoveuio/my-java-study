package org.example.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName SocketTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/12 15:12
 * @Version 1.0
 */
public class SocketTest {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 80);
    }

}
