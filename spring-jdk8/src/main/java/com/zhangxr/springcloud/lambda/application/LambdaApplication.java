package com.zhangxr.springcloud.lambda.application;

import com.zhangxr.springcloud.lambda.Emp;
import com.zhangxr.springcloud.lambda.application.function.MyFunction;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @className LambdaApplication
 * @Description Lambda表达式的应用
 * @Author sdzha
 * @Date 2020/12/24 14:12
 * @Version 1.0
 */
public class LambdaApplication {
    //员工信息
    List<Emp> lists = Arrays.asList(
            new Emp(1,"张三",18,3345.00),
            new Emp(2,"李四",18,2345.00),
            new Emp(3,"王五",22,4345.00),
            new Emp(4,"赵六",22,6345.00),
            new Emp(5,"钱二",22,5345.00)
    );

    /**
     * list转map
     *  key：list[i]某个字段，value：list[i]
     */
    @Test
    public void test1(){
        Map<String, Emp> collect = lists.stream()
                .collect(Collectors.toMap(Emp::getName, Function.identity()));
        System.out.println(collect.toString());
        /*
         * 输出：
         * {
         *      李四=Emp{id=2, name='李四', age=18, salary=2345.0},
         *      张三=Emp{id=1, name='张三', age=33, salary=3345.0},
         *      王五=Emp{id=3, name='王五', age=22, salary=4345.0},
         *      钱二=Emp{id=5, name='钱二', age=45, salary=5345.0},
         *      赵六=Emp{id=4, name='赵六', age=50, salary=6345.0}
         * }
         */
    }
    /**
     * list转map
     *  key：list[i]某个字段，value：list[i]某个字段
     */
    @Test
    public void test2(){
        Map<String, Double> collect = lists.stream()
                .collect(Collectors.toMap(Emp::getName, Emp::getSalary));
        System.out.println(collect.toString());
        /*
         * 输出：
         * {
         *      李四=2345.0,
         *      张三=3345.0,
         *      王五=4345.0,
         *      钱二=5345.0,
         *      赵六=6345.0
         * }
         */
    }

    /***
     * 将list按某个字段分组
     */
    @Test
    public void test3(){
        Map<Integer, List<Emp>> collect = lists.stream()
                .collect(Collectors.groupingBy(Emp::getAge));
        System.out.println(collect.toString());
        /*
         * 输出：
         * {
         *      18=[
         *          Emp{id=1, name='张三', age=18, salary=3345.0},
         *          Emp{id=2, name='李四', age=18, salary=2345.0}
         *      ],
         *      22=[
         *          Emp{id=3, name='王五', age=22, salary=4345.0},
         *          Emp{id=4, name='赵六', age=22, salary=6345.0},
         *          Emp{id=5, name='钱二', age=22, salary=5345.0}
         *      ]
         * }
         */
    }

    /**
     * list排序
     */
    @Test
    public void test4(){
        List<Emp> collect = lists.stream().
                //正序
                sorted(Comparator.comparing(Emp::getSalary))
                .collect(Collectors.toList());
        System.out.println(collect.toString());
        /*
         * [
         *      Emp{id=2, name='李四', age=18, salary=2345.0},
         *      Emp{id=1, name='张三', age=18, salary=3345.0},
         *      Emp{id=3, name='王五', age=22, salary=4345.0},
         *      Emp{id=5, name='钱二', age=22, salary=5345.0},
         *      Emp{id=4, name='赵六', age=22, salary=6345.0}
         * ]
         */

        List<Emp> collect1 = lists.stream().
                sorted(Comparator.comparing(Emp::getSalary).reversed())
                .collect(Collectors.toList());
        System.out.println(collect1.toString());
        /*
         * [
         *      Emp{id=4, name='赵六', age=22, salary=6345.0},
         *      Emp{id=5, name='钱二', age=22, salary=5345.0},
         *      Emp{id=3, name='王五', age=22, salary=4345.0},
         *      Emp{id=1, name='张三', age=18, salary=3345.0},
         *      Emp{id=2, name='李四', age=18, salary=2345.0}
         * ]
         */
    }

    /**
     * List条件filter过滤
     * - 获取list中符合条件的数目
     */
    @Test
    public void test5(){
        long count = lists.stream()
                .filter(emp -> emp.getSalary() > 4000)
                .count();
        System.out.println(count);//3
    }

    /**
     * 获取list中符合条件list
     */
    @Test
    public void test6(){
        List<Emp> collect = lists.stream().
                filter(emp -> emp.getSalary() > 4000)
                .collect(Collectors.toList());
        System.out.println(collect.toString());
        /*
         * 输出：
         * [
         *      Emp{id=3, name='王五', age=22, salary=4345.0},
         *      Emp{id=4, name='赵六', age=22, salary=6345.0},
         *      Emp{id=5, name='钱二', age=22, salary=5345.0}
         * ]
         */
    }

    /**
     * Lambda 对 Map的操作
     */
    @Test
    public void test7(){
        List<Emp> list = new ArrayList<>();
        Map<Integer,Emp> map  = new HashMap<>();
        map.put(1,new Emp(1,"张三",18,3345.00));
        map.put(2,new Emp(2,"李四",18,2345.00));
        map.put(3,new Emp(3,"王五",22,4345.00));
        map.put(4,new Emp(4,"赵六",22,6345.00));
        map.put(5,new Emp(5,"钱二",22,5345.00));
        map.forEach((k,v) -> {
            if (v.getSalary() > 4000) list.add(v);
        });
        System.out.println(list.toString());
        /*
         * [
         *      Emp{id=3, name='王五', age=22, salary=4345.0},
         *      Emp{id=4, name='赵六', age=22, salary=6345.0},
         *      Emp{id=5, name='钱二', age=22, salary=5345.0}
         * ]
         */
    }

    /***
     * 数据运算： 定义MyFunction接口
     */
    @Test
    public void test8(){
        System.out.println(operation(2, (value -> value * value)));
        System.out.println(operation(2, (value -> value + 200)));
    }
    public Integer operation(Integer value,MyFunction myFunction){
        return myFunction.getValue(value);
    }

    /**
     *  自定义定义排序：
     *      先按年龄排，再按薪资排
     */
    @Test
    public void test9(){
        System.out.println("---------- 年龄升序，再按薪资升序 ------------");
        Collections.sort(lists,(e1,e2) -> {
            if (e1.getAge() == e2.getAge()){
                return e1.getSalary().compareTo(e2.getSalary());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        lists.forEach(emp -> System.out.println(emp));

        System.out.println("----------- 年龄降序，再按薪资升序 ------------");
        Collections.sort(lists,(e1,e2) -> {
            if (e1.getAge() == e2.getAge()){
                return e1.getSalary().compareTo(e2.getSalary());
            }else {
                return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        lists.forEach(emp -> System.out.println(emp));

        System.out.println("----------- 年龄升序，再按薪资降序 ------------");
        Collections.sort(lists,(e1,e2) -> {
            if (e1.getAge() == e2.getAge()){
                return -e1.getSalary().compareTo(e2.getSalary());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        lists.forEach(emp -> System.out.println(emp));

        System.out.println("----------- 年龄降序，再按薪资降序 ------------");
        Collections.sort(lists,(e1,e2) -> {
            if (e1.getAge() == e2.getAge()){
                return -e1.getSalary().compareTo(e2.getSalary());
            }else {
                return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        lists.forEach(emp -> System.out.println(emp));
    }
}















