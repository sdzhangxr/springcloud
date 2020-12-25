package com.zhangxr.springcloud.lambda.basicGrammar;

import com.zhangxr.springcloud.lambda.Emp;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @className testLambda3
 * @Description
 * 1.方法引用
 *      若Lambda体中的内容已经实现了，我们可以使用方法引用
 *   三种语法形式：
 *      对象::实例方法名
 *      类::静态方法名
 *      类::实例方法名
 * 2.构造器引用
 * 3.数组引用
 *
 * @Author sdzha
 * @Date 2020/12/25 11:06
 * @Version 1.0
 */
public class testLambda3 {
    //对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("ssssss");
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("dddddd");
    }
    @Test
    public void test2(){
        Emp emp = new Emp(1, "张三", 18, 23000D);
        Supplier<String> stringSupplier = () -> emp.getName();
        String s = stringSupplier.get();
        System.out.println(s);
        Supplier<Integer> integerSupplier = emp::getId;
        Integer integer = integerSupplier.get();
        System.out.println(integer);
    }

    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        int compare = comparator.compare(12, 14);
        System.out.println(compare);
        Comparator<Integer> comparator1 = Integer::compare;
        int compare1 = comparator.compare(14, 12);
        System.out.println(compare1);
    }

    //类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String ,String> biPredicate = (x,y) -> x.equals(y);
        boolean test = biPredicate.test("www", "www");
        System.out.println(test);

        BiPredicate<String,String> biPredicate1 = String::equals;
        boolean test1 = biPredicate.test("sss", "ss");
        System.out.println(test1);
    }

    //构造器引用
    @Test
    public void test5(){
        //调用无参构造
        Supplier<Emp> supplier = () -> new Emp();
        Supplier<Emp> supplier1 = Emp::new;
        //调用有参构造
        Function<Integer,Emp> function = (x) -> new Emp(x);
        Function<Integer,Emp> function1 = Emp::new;
    }

    //数组引用

}
