package com.zhangxr.springcloud.annotation;

import org.junit.Test;
import org.springframework.stereotype.Controller;

import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @className TestAnnotation
 * @Description 可重复注解与类型注解\
 *      要实现重复注解：（在方法上添加两个相同的注解）
 *          1.新建容器注解（MyAnnotations，属性值value为MyAnnotation[]）
 *          2.注解上添加@Repeatable()修饰，参数为容器注解类型（@Repeatable(MyAnnotations.class)）
 *
 * @Author sdzha
 * @Date 2020/12/30 11:00
 * @Version 1.0
 */
@Controller
public class TestAnnotation {

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> aClass = TestAnnotation.class;
        Method show = aClass.getMethod("show");
        MyAnnotation[] annotations = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation annotation : annotations) {
            System.out.println(annotation.name());
        }
        //当存在重复注解.getAnnotation()方法报错
//        MyAnnotation annotation = show.getAnnotation(MyAnnotation.class);
//        System.out.println(annotation.name());

    }

    @MyAnnotation(name = "zhangxr")
    @MyAnnotation(name = "shandong")
    public void show(){

    }
}
