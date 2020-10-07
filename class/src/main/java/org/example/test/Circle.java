package org.example.test;

/**
 * @ClassName Circle
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/7/25 16:50
 * @Version 1.0
 */
public class Circle extends Shape{

    int r;

    @Override
    public double s() {
        return 0;
    }

    public static void main(String[] args) {
        Circle circle = new Circle();

            System.out.println(circle instanceof Shape);
    }
}
