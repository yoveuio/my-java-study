package org.example.designPatterns.structure_model.decorator;

/**
 * @author yoveuio
 * @version 1.0
 * @className DecoratorPattern
 * @description 装饰者模式的简单实现
 * @date 2020/12/23 17:21
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Component p = new ConcreteComponent();
        p.operation();
        Component d = new ConcreteDecorator(p);
        d.operation();
    }

    interface Component{
        void operation();
    }

    /**
     * 具体构件对象
     * 被装饰、实现了某些核心功能的构件
     */
    static class ConcreteComponent implements Component{
        public ConcreteComponent() {
            System.out.println("创建具体构件角色");
        }
        @Override
        public void operation() {
            System.out.println("调用具体构建角色的operation方法");
        }
    }

    static class Decorator implements Component {
        private Component component;

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void operation() {
            component.operation();
        }
    }

    static class ConcreteDecorator extends Decorator {
        public ConcreteDecorator(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            super.operation();
            addedFunction();
        }

        private void addedFunction() {
            System.out.println("为具体角色添加额外的功能:addedFunction");
        }
    }
}
