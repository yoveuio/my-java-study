package org.example.servletl;

import org.example.Request;
import org.example.Response;

import java.io.IOException;

/**
 * @ClassName StaticResourceProcessor
 * @Description 用于处理静态资源的请求
 *  这个类只调用response的sendStaticResource()方法
 * @Author yoveuio
 * @Date 2020/9/13 20:59
 * @Version 1.0
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
