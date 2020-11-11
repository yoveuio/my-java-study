package org.example.designPatterns.structure_model.adapter;


/**
 * @ClassName ObjectAdapter
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/11 17:01
 * @Version 1.0
 */
public class ObjectAdapterTest {
    public static void main(String[] args) {
        System.out.println("对象适配器模式测试");
        ClassAdapterTest.Adaptee adaptee = new ClassAdapterTest.Adaptee();
        ClassAdapterTest.Target target = new ObjectAdapter(adaptee);
        target.request();
    }

    static class ObjectAdapter implements ClassAdapterTest.Target{

        private ClassAdapterTest.Adaptee adaptee;

        public ObjectAdapter(ClassAdapterTest.Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            adaptee.specificRequest();
        }
    }
}
