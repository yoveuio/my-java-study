package org.example.proxy;

/**
 * @ClassName Hello
 * @Description 被代理的类应该继承实现一个接口，并实现接口中的方法
 * @Author yoveuio
 * @Date 2020/11/3 14:52
 * @Version 1.0
 */
public interface Hello {
    /**
     * 打印一条Hello语句
     */
    void sayHello();

    /**
     * 打印再见
     */
    void sayBye();
}
