package org.example.servletl;

import org.example.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName HttpServer2
 * @Description 这是HttpServer1的改进版。因为知晓结构的人可以通过response和request强转来调用sendStaticResource()等方法。
 *  但是又不能通过将sendStaticResource()设为私有方法的方式来改变，因为有其他对象调用了这个方法。
 *  这个后果是因为我们设计的不合理，所以我们需要一个新的结构来实现servlet:外观类
 *  引用程序包含6个类：
 *      HttpServer2，
 *      SimpleServletRequest
 *      SimpleServletResponse
 *      StaticResourceProcessor
 *      servletProcessor2
 *      Constants
 *
 * HttpServer2与HttpServer类似，只是在其await中使用servletProcessor2类而不是servletProcessor类
 * @Author yoveuio
 * @Date 2020/9/17 22:10
 * @Version 1.0
 */
public class HttpServer2 {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
        server.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //创建请求实例
                Request request = new SimpleServletRequest(input);
                request.parse();

                //创建响应实例
                SimpleServletResponse response = new SimpleServletResponse(output);
                response.setRequest(request);

                //处理servlet资源
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                }
                //处理静态资源
                else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                //关闭socket连接
                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
