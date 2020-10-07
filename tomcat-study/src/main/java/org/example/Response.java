package org.example;

import org.example.my_web_server.HttpServer;

import javax.servlet.ServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName Response
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/13 22:01
 * @Version 1.0
 */
public interface Response extends ServletResponse {

    public void setRequest(Request request);

    public void sendStaticResource() throws IOException;

}
