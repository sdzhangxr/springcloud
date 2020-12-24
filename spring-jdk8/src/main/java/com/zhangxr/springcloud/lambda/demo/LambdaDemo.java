package com.zhangxr.springcloud.lambda.demo;

import com.zhangxr.springcloud.lambda.demo.predicate.MyPredicate;
import com.zhangxr.springcloud.lambda.demo.predicate.impl.FilterEmpByAge;
import com.zhangxr.springcloud.lambda.demo.predicate.impl.FilterEmpBySalary;
import com.zhangxr.springcloud.lambda.Emp;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className LambdaDemo
 * @Description Lambda表达式
 * @Author sdzha
 * @Date 2020/12/23 14:02
 * @Version 1.0
 */
public class LambdaDemo {
    //员工信息
    List<Emp> lists = Arrays.asList(
            new Emp(1,"张三",33,3345.00),
            new Emp(2,"李四",18,2345.00),
            new Emp(3,"王五",22,4345.00),
            new Emp(4,"赵六",50,6345.00),
            new Emp(5,"钱二",45,5345.00)
    );

    /**
     * 需求：
     *    过滤员工年龄大于35
     *    过滤员工薪资大于4000
     */

    /**
     * @Author sdzha
     * @Description 调用过滤方法
     * @Date 2020/12/24 9:28
     * @Param [emps, predicate, obj] [需要过滤的集合,过滤规则,条件值]
     * @return java.util.List<com.zhangxr.springcloud.lambda.Emp>
     */
    public List<Emp> filterP(List<Emp> emps, MyPredicate<Emp> predicate){
        List<Emp> resultList = new ArrayList<>();
        for (Emp emp : emps) {
            boolean filter = predicate.filter(emp);
            if (filter) resultList.add(emp);
        }
        return resultList;
    }

    //策略设计模式
    @Test
    public void test1(){
        List<Emp> emps = filterP(lists, new FilterEmpByAge());
        List<Emp> emps1 = filterP(lists, new FilterEmpBySalary());
        for (Emp emp : emps) {
            System.out.println(emp.toString());
        }
        System.out.println("-----------------");
        for (Emp emp : emps1) {
            System.out.println(emp.toString());
        }
    }

    //匿名内部类
    @Test
    public void test2(){
        List<Emp> emps = filterP(lists, new MyPredicate<Emp>() {
            @Override
            public boolean filter(Emp emp) {
                return emp.getAge() > 35;
            }
        });
        for (Emp emp : emps) {
            System.out.println(emp.toString());
        }
        System.out.println("------------");
        List<Emp> emps1 = filterP(lists, new MyPredicate<Emp>() {
            @Override
            public boolean filter(Emp emp) {
                return emp.getSalary() > 4000;
            }
        });
        for (Emp emp : emps1) {
            System.out.println(emp.toString());
        }
    }

    //Lambda表达式
    @Test
    public void test3(){
        List<Emp> emps = filterP(lists, (e) -> e.getAge() > 35);
        List<Emp> emps1 = filterP(lists, (emp -> emp.getSalary() > 4000));
        emps.forEach(System.out::println);
        System.out.println("------------");
        emps1.forEach(System.out::println);
    }

    //stream API
    @Test
    public void test4(){
        lists.stream()
                .filter(emp -> emp.getSalary() > 4000)
                .map(Emp::getName)
                .forEach(System.out::println);
    }
}










