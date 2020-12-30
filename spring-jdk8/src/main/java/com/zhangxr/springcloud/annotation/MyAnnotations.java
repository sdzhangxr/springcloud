package com.zhangxr.springcloud.annotation;

import java.lang.annotation.*;

/**
 * @className MyAnnotations
 * @Description
 * @Author sdzha
 * @Date 2020/12/30 11:00
 * @Version 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotations {
    MyAnnotation[] value();
}
