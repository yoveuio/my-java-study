package org.example.designPatterns.behavioral_model.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className VisitorPattern
 * @description 访问者模式
 * @date 2021/2/16 13:04
 */
public class VisitorPattern {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor = new ConCreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------");
        os.accept(new ConcreteVisitorB());
    }

    interface Visitor{
        void visit(ConcreteElementA element);

        void visit(ConcreteElementB element);
    }

    static class ConCreteVisitorA implements Visitor{
        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者A访问--->" + element.operatorA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者A访问--->" + element.operationB());
        }
    }

    static class ConcreteVisitorB implements Visitor{
        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者B访问--->" + element.operatorA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者B访问--->" + element.operationB());
        }
    }

    interface Element{
        void accept(Visitor visitor);
    }

    static class ConcreteElementA implements Element {

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operatorA() {
            return "具体元素A的操作";
        }
    }

    static class ConcreteElementB implements Element {

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationB() {
            return "具体元素B的操作";
        }
    }

    static class ObjectStructure {
        private List<Element> list = new ArrayList<>();

        public void accept(Visitor visitor) {
            for (Element element : list) {
                element.accept(visitor);
            }
        }

        public void add(Element element) {
            list.add(element);
        }

        public void remove(Element element) {
            list.remove(element);
        }
    }
}
