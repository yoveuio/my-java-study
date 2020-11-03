package org.example.proxy;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @ClassName ProxyTest
 * @Description Java动态代理
 *  通过设计模式中的代理模式的学习。我们可以通过代理对象实现消息的预处理和加工，将执行结果封装实现代码的松耦合
 *  由于代理模式中大多是静态代理，静态代理只能为一个类服务，如果需要代理的类很多就需要写很多个代理类，这时就可以使用java.lang.reflect包提供的动态代理
 *
 *  动态代理具体步骤：
 *      1.通过实现InvocationHandler接口创建自己的调用处理器
 *          InvocationHandler handler = new ProxyHandler(hello);
 *      2.通过为Proxy类指定ClassLoader对象和一组interface来创建动态代理类
 *          Class<?> proxyClass = Proxy.getProxyClass(Hello.class.getClassLoader(), Hello.class);
 *      3.通过反射机制获得动态代理类的构造函数，其唯一的参数类型是调用处理器接口类型
 *          Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
 *      4.通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数传入
 *          Hello proxyHello = (Hello) constructor.newInstance(handler);
 *
 *
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

        /**
         * 代理对象每次调用接口中的方法时，都会访问处理类，看看接口方法是否被代理，如果被代理就调用invoke方法
         * @param proxy 代理的对象
         * @param method 代理对象的方法名(代理的实体)
         * @param args 代理对象的参数列表
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke " + method.getName());
            method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return object;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //将JDK动态代理生成的class文件保存到本地
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //创建真实的对象
        Hello hello = new HelloImpl();
        //代理类：用来代理真实对象的处理类
        InvocationHandler handler = new ProxyHandler(hello);
        //生成代理对象：是代理类处理完之后的对象
        /*
        * public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        * ClassLoader加载真实对象的类加载器,一般用 真实对象.getClass().getClassLoader()即可。这个示例中为: hello.getClass().getClassLoader()
        * Class<?>[] 被这个代理类代理的一系列接口，使用 new Class<?>[]{接口.class}。
        * InvocationHandler：用来处理这个代理的处理类。本示例用ProxyHandler处理
        * */
        Hello proxyHello = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), new Class[]{Hello.class},
                handler);
        proxyHello.sayHello();
        proxyHello.sayBye();
    }
}
