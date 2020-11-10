package org.example.homework.aop_and_annotation.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName RepeatableAnnotation
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/10 20:08
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepeatableAnnotations.class)
public @interface RepeatableAnnotation {

    int a() default 0;
    int b() default 0;
    int c() default 0;
}
