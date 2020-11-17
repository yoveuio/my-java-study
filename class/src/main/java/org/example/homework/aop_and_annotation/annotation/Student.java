package org.example.homework.aop_and_annotation.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName Student
 * @Description 测试可重复Annotation的类
 * @Author yoveuio
 * @Date 2020/11/10 20:11
 * @Version 1.0
 */
public class Student {
    @RepeatableAnnotation(a = 1, b = 2, c = 3)
    @RepeatableAnnotation(a = 1, b = 2, c = 4)
    public static void add(int a, int b, int c) {
        if (c != a + b) {
            throw new ArithmeticException("Wrong");
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<? extends Student> aClass = Student.class;
        Method[] methods = aClass.getMethods();
        int a, b, c;
        for (Method method : methods) {
            if (method.isAnnotationPresent(RepeatableAnnotations.class)) {
                RepeatableAnnotation[] annotations = method.getAnnotation(RepeatableAnnotations.class).value();
                for (RepeatableAnnotation annotation : annotations) {
                    a = annotation.a();
                    b = annotation.b();
                    c = annotation.c();
                    method.invoke(null, a, b, c);
                }
            }
        }
    }
}
