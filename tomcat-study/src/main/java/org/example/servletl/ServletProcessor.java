package org.example.servletl;

import org.example.Request;
import org.example.Response;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @ClassName ServletProcessor
 * @Description 处理对servlet资源的HTTP请求
 * @Author yoveuio
 * @Date 2020/9/13 20:59
 * @Version 1.0
 */
public class ServletProcessor {

    public void process(Request request, Response response) {
        //获取uri, uri的格式如下：/servlet/servletName
        String uri = request.getUri();
        //获取servlet的类名，用以载入相应类
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        //创建类加载器，并指明到哪里查找要载入的类。
        URLClassLoader loader = null;

        try {
            //创建一个URLCLassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            //返回一个符合类加载器的资源路径
            String repository = (new URL("file", null,
                    classPath.getCanonicalPath()+File.separator)).toString();
            //第二个参数指定一个目录。
            //使用streamHandle代替null的原因是，构造方法中还有一个同样可以接收(null, repository, null)的方法
            urls[0] = new URL(null, repository, streamHandler);
            //urls中每一个对象都指明了类加载器要到哪里查找类。若一个URL以"/"结尾，则指向的是一个目录
            //否则URL默认指向一个JAR文件，根据需要加载器会下载并打开这个jar文件。
            //在servlet中，类加载器查找servlet类的目录称为仓库。
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myClass = null;
        try {
            assert loader != null;
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        Servlet servlet = null;
        try {
            assert myClass != null;
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch (IllegalAccessException | InstantiationException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

}
