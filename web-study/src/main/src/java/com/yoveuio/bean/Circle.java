package com.yoveuio.bean;

/**
 * @ClassName Circle
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/19 15:43
 * @Version 1.0
 */
public class Circle {

    String utl;
    String servletName;
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI * radius *radius;
    }
}
