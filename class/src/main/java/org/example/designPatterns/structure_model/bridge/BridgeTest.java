package org.example.designPatterns.structure_model.bridge;

/**
 * @ClassName BridgeTest
 * @Description 桥接模式
 * @Author yoveuio
 * @Date 2020/11/11 17:49
 * @Version 1.0
 */
public class BridgeTest {

    /**
     * 实现化角色
     */
    interface Implementor {
        public void OperationImpl();
    }

    /**
     * 具体实现化角色
     */
    static class ConcreteImplementorA implements Implementor {

        @Override
        public void OperationImpl() {
            System.out.println("具体实现化(Concrete Implementor)角色被访问");
        }
    }

    /**
     * 抽象化角色
     */
    static abstract class Abstraction {
        protected Implementor implementor;
        protected Abstraction(Implementor implementor) {
            this.implementor = implementor;
        }
        public abstract void operation();
    }

    /**
     * 扩展抽象化角色
     */
    static class RefinedAbstraction extends Abstraction {

        protected RefinedAbstraction(Implementor implementor) {
            super(implementor);
        }

        @Override
        public void operation() {
            System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
            implementor.OperationImpl();
        }
    }
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(implementor);
        abs.operation();
    }
}
