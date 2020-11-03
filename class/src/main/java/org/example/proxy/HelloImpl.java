package org.example.proxy;

/**
 * @ClassName HellpImpl
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/3 14:59
 * @Version 1.0
 */
public class HelloImpl implements Hello{
    @Override
    public void sayHello() {
        System.out.println("Hello world!");
    }

    @Override
    public void sayBye() {
        System.out.println("Good Bye!");
    }
}
