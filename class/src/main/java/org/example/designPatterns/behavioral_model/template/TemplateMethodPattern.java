package org.example.designPatterns.behavioral_model.template;

/**
 * @author yoveuio
 * @version 1.0
 * @className TemplateMethodPattern
 * @description 模板方法模式
 * @date 2021/1/31 17:15
 */
public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.templateMethod();
    }

    static abstract class AbstractClass {
        // 模板方法
        public void templateMethod() {
            specificMethod();
            abstractMethod1();
            abstractMethod2();
        }

        // 具体方法
        public void specificMethod() {
            System.out.println("抽象类中的具体方法调用");
        }

        // 抽象方法1
        public abstract void abstractMethod1();

        // 抽象方法2
        public abstract void abstractMethod2();
    }

    static class ConcreteClass extends AbstractClass {

        @Override
        public void abstractMethod1() {
            System.out.println("抽象方法1的实现被调用");
        }

        @Override
        public void abstractMethod2() {
            System.out.println("抽象方法2的实现被调用");
        }
    }
}
