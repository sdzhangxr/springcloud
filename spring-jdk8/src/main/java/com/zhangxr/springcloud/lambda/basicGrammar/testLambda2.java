package com.zhangxr.springcloud.lambda.basicGrammar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @className testLambda2
 * @Description 内置核心函数式接口
 *      1. Consumer<T> : 消费型接口
 *          void accept(T t);
 *      2.Supplier<T> : 供给型接口
 *          T get();
 *      3.Function<T, R> : 函数型接口
 *          R apply(T t);
 *      4.Predicate<T> : 断言型接口
 *          boolean test(T t);
 * @Author sdzha
 * @Date 2020/12/25 10:00
 * @Version 1.0
 */
public class testLambda2 {
    //Consumer<T> : 消费型接口
    @Test
    public void testConsumer(){
        happy(100D,(c) -> System.out.println("支付："+ c+"，花费：80，找零："+ (c-80)));
    }

    public void happy(Double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

    //Supplier<T> : 供给型接口
    @Test
    public void testSupplier(){
        List<Integer> list = getNumList(10, () -> {
            int num = (int) (Math.random() * 100);
            return num;
        });
        System.out.println(list.toString());
    }

    public List<Integer> getNumList(Integer length,Supplier<Integer> supplier){
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            resultList.add(supplier.get());
        }
        return resultList;
    }

    //Function<T, R> : 函数型接口
    @Test
    public void testFunction(){
        String content = "\t\t\t  test  ";
        String str = getString(content,(s) -> s.trim());
        System.out.println(str);
    }

    public String getString(String str,Function<String,String> function){
        return function.apply(str);
    }

    //Predicate<T> : 断言型接口
    @Test
    public void testPredicate(){
        List<String> list = Arrays.asList(
                "hello","www","ok","Lambda"
        );
        List<String> resultList = filterString(list, (s) -> s.length() > 3);
        System.out.println(resultList.toString());
    }
    public List<String> filterString(List<String> list,Predicate<String> predicate){
        List<String> resultList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) resultList.add(s);
        }
        return resultList;
    }
}


















