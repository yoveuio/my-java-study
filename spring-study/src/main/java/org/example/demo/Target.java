package org.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

/**
 * @ClassName Target
 * @Description 目标对象，有切入点，少了逻辑代码，即通知
 * @Author yoveuio
 * @Date 2020/10/29 21:07
 * @Version 1.0
 */
public class Target {


    public void method1(){
        //System.out.println(1); //共性功能,制作通知
        System.out.println(2);
        System.out.println(3);
    }


    public void method2() {

    }
}
