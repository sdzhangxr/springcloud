package com.zhangxr.springcloud.stream;

import com.zhangxr.springcloud.lambda.Emp;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className IntermediateOperation
 * @Description StreamAPI：中间操作Stream
 *      1.筛选与切片
 *          filter
 *          limit
 *          skip
 *          distinct
 *     2.映射
 *          map
 *          flatMap
 *
 * @Author sdzha
 * @Date 2020/12/25 15:31
 * @Version 1.0
 */
public class IntermediateOperation {
    Emp[] emps = {
            new Emp(1,"张三",33,3345.00),
            new Emp(2,"李四",18,2345.00),
            new Emp(3,"王五",22,4345.00),
            new Emp(4,"赵六",50,6345.00),
            new Emp(4,"赵六",50,6345.00),
            new Emp(5,"钱二",45,5345.00)
    };
    //员工信息
    List<Emp> lists = Arrays.asList(emps);

    /**
     * 筛选与切片：
     *  filter
     *  limit
     *  skip
     *  distinct
     */
    //.filter((emp) -> emp.getAge() > 35) 获取35以上的
    @Test
    public void test1(){
        lists.stream()                              //创建
                .filter((emp)-> emp.getAge() > 35)  //中间操作
                .forEach(System.out::println);      //中止操作
        System.out.println("---------------------------------------");
        List<Emp> collect = lists.stream()
                .filter((emp) -> emp.getAge() > 35)
                .collect(Collectors.toList());
        System.out.println(collect.toString());
    }
    //.limit(2) 截取前两个
    @Test
    public void test2(){
        lists.stream()
                .filter((emp) -> emp.getAge() < 35)
                .limit(2)
                .forEach(System.out::println);
    }
    //.skip(2) 跳过前两个
    @Test
    public void test3(){
        lists.stream()
                .filter((emp) -> emp.getAge() < 35)
                .skip(2)
                .forEach(System.out::println);
    }
    //.distinct()  注意：去重比较的对象，要重写hashCode与equals
    @Test
    public void test4(){
        lists.stream()
                .filter((emp) -> emp.getAge() > 35)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     *      map
     *      flatMap
     */
    @Test
    public void test5(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");
        //修改信息
        list.stream()
                .map((s) -> s.toUpperCase())
                .forEach(System.out::println);
        //提取信息
        lists.stream()
                .map((emp) -> emp.getName())
                .forEach(System.out::println);
        lists.stream()
                .map(Emp::getSalary)
                .forEach(System.out::println);

    }
}
