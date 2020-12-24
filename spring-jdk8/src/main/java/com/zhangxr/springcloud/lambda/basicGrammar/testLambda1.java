package com.zhangxr.springcloud.lambda.basicGrammar;

import org.junit.Test;

import java.util.Comparator;

/**
 * @className testLambda1
 * @Description 基础语法
 *    1.新增 "->" 箭头操作符 / Lambda操作符
 *      箭头左侧：参数列表
 *      箭头右侧：执行功能（Lambda体）
 *    2.Lambda表达式需要 "函数式接口" 的支持
 *      函数式接口: 接口中只能有一个抽象方法,需添加@FunctionalInterface注释
 * @Author sdzha
 * @Date 2020/12/24 10:52
 * @Version 1.0
 */
public class testLambda1 {

    //语法格式1：无参无返回值
    @Test
    public void test1(){
        int num = 0;
        /**
         * 匿名内部类引用同级别的变量，jdk1.7前，变量前加 final 修饰
         */
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1: " + num);
            }
        };
        r1.run();

        System.out.println("-----------------------------");

        Runnable r2 = () -> System.out.println("r2: " + num);
        r2.run();
    }

    //语法格式2：多个参数，Lambda中有多行，有返回值
    @Test
    public void test2(){
        Comparator<Integer> com = (x,y) -> {
            System.out.println(x + "," + y);
            return Integer.compare(x,y);
        } ;
        int compare = com.compare(4, 3);
        System.out.println(compare);
    }

    //语法格式3：多个参数，Lambda中有一行，有返回值 {}和return都可以省
}
