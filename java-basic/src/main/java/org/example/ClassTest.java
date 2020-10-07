package org.example;


import org.junit.Test;

/**
 * @ClassName ClassTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/5 14:02
 * @Version 1.0
 */
public class ClassTest {

    @Test
    public void ClassFunctionTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassTest classTest = new ClassTest();

        Class<?> aClass = Class.forName("org.example.ClassTest");
        //获取实例化对象
        aClass.newInstance();
        //获取对象的全限定类名
        String name = aClass.getName();

        System.out.println(name);
        aClass = ClassTest.class;

    }
}

class Shape{}
class Circle extends Shape{}