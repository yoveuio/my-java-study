package org.example.designPatterns.structure_model.facade;

/**
 * @ClassName FacadeTest
 * @Description 外观模式测试类
 * @Author yoveuio
 * @Date 2020/11/18 17:47
 * @Version 1.0
 */
public class FacadeTest {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }

    static class Facade{
        private Subsystem1 obj1 = new Subsystem1();
        private Subsystem2 obj2 = new Subsystem2();

        public void method(){
            obj1.operation1();
            obj2.operation2();
        }
    }

    static class Subsystem1{
        public void operation1(){
            System.out.println("执行方法1");
        }
    }

    static class Subsystem2{
        public void operation2(){
            System.out.println("执行方法2");
        }
    }
}
