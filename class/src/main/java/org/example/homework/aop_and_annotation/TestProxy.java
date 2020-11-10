package org.example.homework.aop_and_annotation;

/**
 * @ClassName TestProxy
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/10 19:45
 * @Version 1.0
 */
public class TestProxy {

    public static void main(String[] args) throws Exception {
        PersonInf t1 = new PersonSQLImp();
        //以指定的target来创建动态代理
        PersonInf p = (PersonInf) MyProxyFactory.getProxy(t1);
        p.add();
        p.delete();
        p.save();
        p.update();

        Dog t2 = new GunDog();
        Dog dog = (Dog) MyProxyFactory.getProxy(t2);
        dog.info();
        dog.run();


    }
}