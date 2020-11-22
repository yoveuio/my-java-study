package com.yoveuio.view;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName LifeServlet
 * @Description Servlet生命周期的讨论
 * @Author yoveuio
 * @Date 2020/11/19 21:36
 * @Version 1.0
 */
public class LifeServlet extends GenericServlet {
    private int initVar=0;
    private int serviceVar=0;
    private int destroyVar=0;
    private String name;

    public LifeServlet(){
        System.out.println("LifeServlet create");
    }

    public void init (ServletConfig config)throws ServletException {
        name=config.getServletName();
        initVar++;
        System.out.println(name+">init(): Servlet被初始化了"+initVar+"次");
    }
    public void destroy(){
        destroyVar++;
        System.out.println(name+">destroy(): Servlet被销毁了"+destroyVar+"次");
    }
    public void service(ServletRequest request, ServletResponse response)
            throws IOException,ServletException{
        serviceVar++;
        System.out.println(name+">service(): Servlet共响应了"+serviceVar+"次请求");

        String content1="初始化次数 : "+initVar;
        String content2="响应客户请求次数 : "+serviceVar;
        String content3="销毁次数 : "+destroyVar;

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.print("<html><head><title>LifeServlet</title>");
        out.print("</head><body>");
        out.print("<h1>"+content1 +"</h1>");
        out.print("<h1>"+content2 +"</h1>");
        out.print("<h1>"+content3 +"</h1>");
        out.print("</body></html>");
        out.close();
    }
}
