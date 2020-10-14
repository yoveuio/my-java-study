package org.example.designPatterns;

/**
 * @ClassName Decorator
 * @Description 装饰者模式
 *  使用一个装饰者当作中间人，使得客户端不直接操作系统实体，通过装饰着得到想要的对象
 * @Author yoveuio
 * @Date 2020/10/14 17:48
 * @Version 1.0
 */
public class ShapeDecoratorImpl extends ShapeDecorator{

    public ShapeDecoratorImpl(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }

    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeDecorator redCircle = new ShapeDecoratorImpl(new Circle());
        ShapeDecorator redRectangle = new ShapeDecoratorImpl(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();
        redCircle.draw();
        redRectangle.draw();
    }
}

interface Shape{
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {

    }
}

class Circle implements Shape{

    @Override
    public void draw() {

    }
}

abstract class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}
