package org.example.my_web_server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName Response
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/12 16:20
 * @Version 1.0
 */
public class Response {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * 发送一个静态资源到浏览器
     * @throws IOException
     */
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        try {
            //通过父路径和子路径实例化File类
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                //文件找到了将文件写入流
                fis = new FileInputStream(file);
                //将文件加载进内存
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while(ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            }
            else {
                //文件没找到
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

    }

}
