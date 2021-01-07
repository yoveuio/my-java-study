package org.example.designPatterns.structure_model.Flyweight;

import java.util.HashMap;

/**
 * @author yoveuio
 * @version 1.0
 * @className FlyweightDemo
 * @description 享元模式的实现
 * @date 2021/1/7 21:22
 */
public class FlyweightDemo {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight f01 = factory.getFlyweight("a");
        Flyweight f02 = factory.getFlyweight("a");
        Flyweight f03 = factory.getFlyweight("a");
        Flyweight f11 = factory.getFlyweight("b");
        Flyweight f12 = factory.getFlyweight("b");

        f01.operate(new UnsharedConcreteFlyweight("第一次被调用"));
        f02.operate(new UnsharedConcreteFlyweight("第二次被调用"));
        f03.operate(new UnsharedConcreteFlyweight("第三次被调用"));
        f11.operate(new UnsharedConcreteFlyweight("第四次被调用"));
        f12.operate(new UnsharedConcreteFlyweight("第五次被调用"));
    }

    /**
     * 非享元角色，用于存储元素独立的信息
     * 相当于普通的entity
     */
    static class UnsharedConcreteFlyweight {
        private String info;

        public UnsharedConcreteFlyweight(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 抽象类
     * 包含享元方法
     */
    interface Flyweight {
        void operate(UnsharedConcreteFlyweight state);
    }

    /**
     * 享元实现类
     * 相当于将共享的内容拿出来缓存
     * 对entity进行额外的补充修饰，称为一个完整的类
     */
    static class ConcreteFlyweight implements Flyweight {
        private final String key;

        public ConcreteFlyweight(String key) {
            this.key = key;
            System.out.println("具体享元" + key + "被创建");
        }

        @Override
        public void operate(UnsharedConcreteFlyweight outState) {
            System.out.println("具体享元" + key + "被调用");
            System.out.println("非享元信息是:" + outState.getInfo());
        }
    }

    /**
     * 享元工厂类
     */
    static class FlyweightFactory {
        private HashMap<String, Flyweight> flyweights = new HashMap<>();

        public Flyweight getFlyweight(String key) {
            Flyweight flyweight = flyweights.get(key);
            if (flyweight != null) {
                System.out.println("享元" + key + "已经存在，被成功获取");
            }
            else {
                flyweight = new ConcreteFlyweight(key);
                flyweights.put(key, flyweight);
            }
            return flyweight;
        }
    }
}
