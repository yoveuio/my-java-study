package org.example.homework.aop_and_annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName MyProxyFactory
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/10 19:45
 * @Version 1.0
 */
public class MyProxyFactory {

    static class MyProxyHandler implements InvocationHandler{

        //通知类
        public static AspectJTest aspect = new AspectJTest();
        //目标对象
        public Object object;

        public MyProxyHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            aspect.before();
            Object invoke = method.invoke(object, args);
            aspect.after();
            return invoke;
        }
    }


    public static Object getProxy(Object t1) {
        MyProxyHandler handler = new MyProxyHandler(t1);
        return Proxy.newProxyInstance(t1.getClass().getClassLoader(), t1.getClass().getInterfaces(), handler);
    }
}
