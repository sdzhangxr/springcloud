package com.zhangxr.springcloud.stream;

import com.zhangxr.springcloud.lambda.Emp;
import com.zhangxr.springcloud.stream.entity.Trader;
import com.zhangxr.springcloud.stream.entity.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @className TestStream
 * @Description Stream 案例
 * @Author sdzha
 * @Date 2020/12/28 10:48
 * @Version 1.0
 */
public class TestStream {

    Emp[] emps = {
            new Emp(1,"张三",33,3345.00, Emp.Status.FREE),
            new Emp(2,"李四",18,2345.00, Emp.Status.BUSY),
            new Emp(3,"王五",22,4345.00, Emp.Status.VOCATION),
            new Emp(4,"赵六",50,6345.00, Emp.Status.FREE),
            new Emp(5,"田七",45,5345.00, Emp.Status.BUSY)
    };
    //员工信息
    List<Emp> lists = Arrays.asList(emps);

    /**
     * 给定数字列表，返回每个数字平方列表
     *  [1,2,3,4,5]  ==> [1,4,9,16,25]
     */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> collect = list.stream()
                .map((x) -> x * x)
                .collect(Collectors.toList());
        System.out.println(collect.toString());
    }
    /**
     * 利用map和reduce，计算员工个数
     *
     */
    @Test
    public void test2(){
        Optional<Integer> reduce = lists.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());
    }

    Trader raoul = new Trader("Raoul","Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> list = Arrays.asList(
            new Transaction(brian,2011,300),
            new Transaction(raoul,2012,1000),
            new Transaction(raoul,2011,400),
            new Transaction(mario,2012,710),
            new Transaction(mario,2012,700),
            new Transaction(alan,2012,950)
    );

    @Test
    public void test3(){
        System.out.println("------1.找出2011年的所有交易并按交易额升序排列------");
        list.stream()
                .filter((t) -> t.getYear() == 2011)
                .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()))
                .forEach(System.out::println);

        System.out.println("------2.交易员在哪些尝试工作------");
        list.stream()
                .map((t) -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println("------3.在剑桥的交易员，按姓名排序------");
        list.stream()
                .map(Transaction::getTrader)
                .filter((t) -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted((t1,t2) -> t1.getName().compareTo(t2.getName()))
                .forEach(System.out::println);

        System.out.println("------4.所有交易员的姓名，按字母排序------");
        list.stream()
                .map((t) -> t.getTrader().getName())
                .sorted((t1,t2) -> t1.compareTo(t2))
                .distinct()
                .forEach(System.out::println);

    }

}












