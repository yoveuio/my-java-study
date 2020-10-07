package org.example.servletl;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @ClassName PrimitiveServlet
 * @Description 用来测试servlet容器应用程序。
 *  在调用init(),service(),destroy()方法时，Servlet都会将方法名写入标准控制台。
 *  在执行service方法时会从servletResponse对象中获取java.io.printWriter对象，并将字符串发送到客户端浏览器
 * @Author yoveuio
 * @Date 2020/9/13 19:53
 * @Version 1.0
 */
public class PrimitiveServlet implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = servletResponse.getWriter();
        out.println("hello.Roses are red");
        out.print("Violets are blue");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}