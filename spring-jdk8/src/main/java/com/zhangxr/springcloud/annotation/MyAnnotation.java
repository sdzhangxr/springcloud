package com.zhangxr.springcloud.annotation;

import java.lang.annotation.*;

/**
 * @className MyAnnotation
 * @Description
 * @Author sdzha
 * @Date 2020/12/30 11:00
 * @Version 1.0
 */
@Repeatable(MyAnnotations.class)
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String name() default "";
}
