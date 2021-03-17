package org.example.demo;

import org.aspectj.lang.annotation.Around;

/**
 * @ClassName MyAdvice
 * @Description 通知类，抽取的目标对象中的逻辑代码
 * @Author yoveuio
 * @Date 2020/10/29 21:10
 * @Version 1.0
 */
public class MyAdvice {

    @Around("execution(void *..method*())")
    public void printOne() {
        System.out.println(1);
    }
}
