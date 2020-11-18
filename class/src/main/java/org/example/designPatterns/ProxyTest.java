package org.example.designPatterns;

/**
 * @ClassName ProxyTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/18 17:42
 * @Version 1.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.request();
    }

    static abstract class Subject{
        public void request(){
            System.out.println("执行方法");
        }
    }

    static class RealSubject extends Subject{
        @Override
        public void request() {
            System.out.println("执行真实对象的方法");
        }
    }

    static class Proxy extends Subject {
        Subject obj;

        @Override
        public void request() {
            if (obj == null) {
                obj = new RealSubject();
            }
            obj.request();
        }
    }
}
