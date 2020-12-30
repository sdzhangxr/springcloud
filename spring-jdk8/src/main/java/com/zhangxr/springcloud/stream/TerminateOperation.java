package com.zhangxr.springcloud.stream;

import com.zhangxr.springcloud.lambda.Emp;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @className AbortOperation
 * @Description StreamAPI：终止操作
 *      allMatch:   检查是否匹配所有元素
 *      anyMatch:   检查是否至少匹配一个元素
 *      noneMatch:  检查是否没有匹配所有元素
 *      findFirst:  返回第一个元素
 *      findAny:    返回流中任意元素
 *      count:      返回流中元素个数
 *      max:        返回流中最大值
 *      min:        返回流中最小值
 * @Author sdzha
 * @Date 2020/12/25 16:33
 * @Version 1.0
 */
public class TerminateOperation {
    Emp[] emps = {
            new Emp(1,"张三",33,3345.00, Emp.Status.FREE),
            new Emp(2,"李四",18,2345.00, Emp.Status.BUSY),
            new Emp(3,"王五",22,4345.00, Emp.Status.VOCATION),
            new Emp(4,"赵六",50,6345.00, Emp.Status.FREE),
            new Emp(5,"田七",45,5345.00, Emp.Status.BUSY)
    };
    //员工信息
    List<Emp> lists = Arrays.asList(emps);


    @Test
    public void test1(){
        //allMatch: 检查是否匹配所有元素
        boolean allMatch = lists.stream()
                .allMatch((x) -> x.getStatus().equals(Emp.Status.BUSY));
        System.out.println(allMatch);

        //anyMatch:   检查是否至少匹配一个元素
        boolean anyMatch = lists.stream()
                .anyMatch((x) -> x.getStatus().equals(Emp.Status.BUSY));
        System.out.println(anyMatch);

        //noneMatch:  检查是否没有匹配所有元素
        boolean noneMatch = lists.stream()
                .noneMatch((x) -> x.getStatus().equals(Emp.Status.BUSY));
        System.out.println(noneMatch);

        // findFirst:  返回第一个元素
        Optional<Emp> first = lists.stream()
                .sorted((e1, e2) -> -e1.getSalary().compareTo(e2.getSalary()))
                .findFirst();
        System.out.println(first.get().toString());

        //findAny:    返回流中任意元素
        Optional<Emp> any = lists.stream()
                .filter((x) -> x.getStatus().equals(Emp.Status.BUSY))
                .findAny();
        System.out.println(any.get().toString());

        //count:      返回流中元素个数
        long count = lists.stream()
                .count();
        System.out.println(count);

        //max:        返回流中最大值
        Optional<Emp> max = lists.stream()
                .max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(max.get().toString());

        //min:        返回流中最小值
        Optional<Double> min = lists.stream()
                .map(Emp::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }


    /**
     * reduce : 归约（将流中的元素反复结合起来，得到一个值）
     *  .reduce(T identity, BinaryOperator<T> accumulator)
     */
    @Test
    public void test2(){
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        Integer reduce = integerList.stream()
                .reduce(0, (x, y) -> x + y);
        Optional<Integer> reduce1 = integerList.stream().reduce(Integer::sum);
        System.out.println(reduce);
        System.out.println(reduce1.get());
        // 0 为起始值

        //计算薪资总和
        Optional<Double> reduce2 = lists.stream()
                .map(Emp::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce2.get());
    }

    /**
     * 收集
     *   .collect()
     */
    @Test
    public void test3(){
        List<String> listCollect = lists.stream()
                .map(Emp::getName)
                .collect(Collectors.toList());
        System.out.println(listCollect.toString());

        Set<String> setCollect = lists.stream()
                .map(Emp::getName)
                .collect(Collectors.toSet());
        System.out.println(setCollect.toString());

        HashSet<String> hashSetCollect = lists.stream()
                .map(Emp::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashSetCollect.toString());

        //薪资平均值
        Double aDouble = lists.stream()
                .collect(Collectors.averagingDouble(Emp::getSalary));
        System.out.println(aDouble);
        //薪资总和
        Double aDouble1 = lists.stream()
                .collect(Collectors.summingDouble(Emp::getSalary));
        System.out.println(aDouble1);

        Optional<Emp> max = lists.stream()
                .collect(Collectors.maxBy((x, y) -> Double.compare(x.getSalary(), y.getSalary())));
        System.out.println(max.get());

        Optional<Double> min = lists.stream()
                .map(Emp::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    /**
     * 分组
     */
    @Test
    public void test4(){
        Map<Emp.Status, List<Emp>> collect = lists.stream()
                .collect(Collectors.groupingBy(Emp::getStatus));
        System.out.println(collect.toString());
        /**
         * {
         *  BUSY=[
         *      Emp{id=2, name='李四', age=18, salary=2345.0, status=BUSY},
         *      Emp{id=5, name='田七', age=45, salary=5345.0, status=BUSY}
         *  ],
         *  FREE=[
         *      Emp{id=1, name='张三', age=33, salary=3345.0, status=FREE},
         *      Emp{id=4, name='赵六', age=50, salary=6345.0, status=FREE}
         *  ],
         *  VOCATION=[
         *      Emp{id=3, name='王五', age=22, salary=4345.0, status=VOCATION}
         *  ]
         * }
         */
        Map<String, List<Emp>> collect1 = lists.stream()
                .collect(Collectors.groupingBy((e) -> {
                    if (e.getAge() < 30) {
                        return "青年";
                    } else if (30 <= e.getAge() && e.getAge() < 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                }));
        System.out.println(collect1.toString());

        /**
         * {
         *      青年=[
         *          Emp{id=2, name='李四', age=18, salary=2345.0, status=BUSY},
         *          Emp{id=3, name='王五', age=22, salary=4345.0, status=VOCATION}
         *      ],
         *      老年=[
         *          Emp{id=4, name='赵六', age=50, salary=6345.0, status=FREE}
         *      ],
         *      中年=[
         *          Emp{id=1, name='张三', age=33, salary=3345.0, status=FREE},
         *          Emp{id=5, name='田七', age=45, salary=5345.0, status=BUSY}
         *      ]
         * }
         */
    }

    /**
     * 分区
     */
    @Test
    public void test5(){
        Map<Boolean, List<Emp>> collect = lists.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
        System.out.println(collect.toString());
        /**
         *  {
         *      false=[
         *          Emp{id=1, name='张三', age=33, salary=3345.0, status=FREE},
         *          Emp{id=2, name='李四', age=18, salary=2345.0, status=BUSY},
         *          Emp{id=3, name='王五', age=22, salary=4345.0, status=VOCATION}
         *      ],
         *      true=[
         *          Emp{id=4, name='赵六', age=50, salary=6345.0, status=FREE},
         *          Emp{id=5, name='田七', age=45, salary=5345.0, status=BUSY}
         *      ]
         *  }
         */
    }
    @Test
    public void tset6(){
        DoubleSummaryStatistics collect = lists.stream()
                .collect(Collectors.summarizingDouble(Emp::getSalary));
        double average = collect.getAverage();
        long count = collect.getCount();
        double max = collect.getMax();
        double min = collect.getMin();
        double sum = collect.getSum();
    }
    
    @Test
    public void test7(){
        String collect = lists.stream()
                .map(Emp::getName)
                .collect(Collectors.joining(",","(",")"));
        System.out.println(collect);
    }
}
