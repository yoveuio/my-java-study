package org.example.designPatterns.structure_model.adapter;

/**
 * @ClassName ClassAdapterTest
 * @Description 类结构型适配器
 * @Author yoveuio
 * @Date 2020/11/11 16:55
 * @Version 1.0
 */
public class ClassAdapterTest {

    public static void main(String[] args) {
        System.out.println("类适配器模式测试:");
        Target target = new ClassAdapter();
        target.request();
    }

    interface Target{
        public void request();
    }

    static class Adaptee{
        public void specificRequest(){
            System.out.println("适配者中的业务代码被调用");
        }
    }

    static public class ClassAdapter extends Adaptee implements Target {

        @Override
        public void request() {
            specificRequest();
        }
    }
}
