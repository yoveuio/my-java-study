package org.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @ClassName ProxyTest
 * @Description Java动态代理
 *  通过设计模式中的代理模式的学习。我们可以通过代理对象实现消息的预处理和加工，将执行结果封装实现代码的松耦合
 *  由于代理模式中大多是静态代理，静态代理只能为一个类服务，如果需要代理的类很多就需要写很多个代理类，这时就可以使用java.lang.reflect包提供的动态代理
 * @Author yoveuio
 * @Date 2020/11/3 14:44
 * @Version 1.0
 */
public class ProxyTest {

    static class ProxyHandler implements InvocationHandler {
        private Object object;

        public ProxyHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("Before invoke " + method.getName());
            method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return object;
        }
    }

    public static void main(String[] args) {
        //将JDK动态代理生成的class文件保存到本地
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Hello hello = new HelloImpl();

        InvocationHandler handler = new ProxyHandler(hello);

        Hello proxyHello = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(),
                handler);

        proxyHello.sayHello();
        proxyHello.sayBye();
    }

}
