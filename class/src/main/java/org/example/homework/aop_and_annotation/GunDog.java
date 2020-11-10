package org.example.homework.aop_and_annotation;

/**
 * @ClassName GunDog
 * @Description Dog接口的实现类
 * @Author yoveuio
 * @Date 2020/11/10 19:42
 * @Version 1.0
 */
public class GunDog implements Dog{


    @Override
    public void info() {
        System.out.println("我是一只猎狗");
    }

    @Override
    public void run() {
        System.out.println("我奔跑迅捷");
    }
}
