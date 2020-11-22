package com.yoveuio.view;

import com.yoveuio.bean.Circle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName Circle
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/19 15:40
 * @Version 1.0
 */
public class CircleServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String radius = req.getParameter("radius");
        Circle circle = new Circle(Double.parseDouble(radius));
        PrintWriter out = resp.getWriter();
        out.println(circle.getArea());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}