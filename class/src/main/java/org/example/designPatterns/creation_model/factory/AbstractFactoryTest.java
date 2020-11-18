package org.example.designPatterns.creation_model.factory;

/**
 * @ClassName Main
 * @Description 工厂方法模式
 * @Author yoveuio
 * @Date 2020/11/18 16:36
 * @Version 1.0
 */
public class AbstractFactoryTest {

    //抽象产品
    interface Product {
        void show();
    }

    static class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品1展示");
        }
    }

    static class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品2展示");
        }
    }

    interface AbstractFactory{
        Product newProduct();
    }

    static class ConcreteFactory1 implements AbstractFactory {
        @Override
        public Product newProduct() {
            System.out.println("具体工厂1生成-->具体产品1");
            return new ConcreteProduct1();
        }
    }

    static class ConcreteFactory2 implements AbstractFactory {
        @Override
        public Product newProduct() {
            System.out.println("具体工厂2生成-->具体产品2");
            return new ConcreteProduct2();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Product product;
        AbstractFactory factory;
        factory = (AbstractFactory) Class.forName("org.example.designPatterns.creation_model.factory." +
                "AbstractFactoryTest$ConcreteFactory1").newInstance();
        product = factory.newProduct();
        product.show();
    }
}
