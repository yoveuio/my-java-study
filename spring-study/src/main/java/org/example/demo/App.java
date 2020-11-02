package org.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName App
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/1 10:59
 * @Version 1.0
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Target target = (Target) applicationContext.getBean("target");
        target.method1();
        target.method2();
    }
}
