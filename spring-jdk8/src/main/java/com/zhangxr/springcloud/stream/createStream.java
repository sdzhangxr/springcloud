package com.zhangxr.springcloud.stream;

import com.zhangxr.springcloud.lambda.Emp;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @className createStream
 * @Description StreamAPI：创建Stream
 * @Author sdzha
 * @Date 2020/12/25 15:14
 * @Version 1.0
 */
public class createStream {
    Emp[] emps = {
            new Emp(1,"张三",33,3345.00),
            new Emp(2,"李四",18,2345.00),
            new Emp(3,"王五",22,4345.00),
            new Emp(4,"赵六",50,6345.00),
            new Emp(5,"田七",45,5345.00)
    };
    //员工信息
    List<Emp> lists = Arrays.asList(emps);

    //创建流
    @Test
    public void test1(){
        Stream<Emp> listStream = lists.stream();
        Stream<Emp> empStream = Arrays.stream(emps);
        Stream<Emp> emps = Stream.of(this.emps);
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        iterate.limit(10).forEach(System.out::println);
    }


}
