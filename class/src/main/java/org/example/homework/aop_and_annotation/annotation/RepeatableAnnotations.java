package org.example.homework.aop_and_annotation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName RepeatableAnnotations
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/10 20:09
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatableAnnotations {
    RepeatableAnnotation[] value();
}
