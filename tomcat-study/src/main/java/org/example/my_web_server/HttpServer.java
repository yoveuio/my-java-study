package org.example.my_web_server;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName HttpServer
 * @Description 表示一个Web服务器
 * @Author yoveuio
 * @Date 2020/9/12 15:26
 * @Version 1.0
 */
public class HttpServer {

    /**
     * System.getProperty方法可以用来读取JVM中的系统属性。
     * System.getProperty("user.dir")用来获取当前程序所在的目录。
     * 该代码清单中包含了一个名为webroot的目录，用于存放测试该应用程序的一些静态资源都位于该目录
     * 浏览器能够访问的静态资源都在这个目录下方。
     *  如 http://localhost:8080/index.html。
     *     index.html页面就位于webroot，目录下方。
     */
    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "webroot";

    /**
     * 关闭命令，如果服务器接收到了浏览器发送的接收命令，就会关闭服务器。
     */
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) throws InterruptedException {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            //创建一个ServerSocket对象
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //接受请求
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                //接到请求后就对报文进行处理
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //创建请求体
                Request request = new Request(input);
                request.parse();

                //创建响应体
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
