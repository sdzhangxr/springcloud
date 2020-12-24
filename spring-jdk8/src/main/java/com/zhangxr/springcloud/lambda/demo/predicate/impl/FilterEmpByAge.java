package com.zhangxr.springcloud.lambda.demo.predicate.impl;

import com.zhangxr.springcloud.lambda.Emp;
import com.zhangxr.springcloud.lambda.demo.predicate.MyPredicate;

/**
 * @className impl
 * @Description 自定义过滤器接口，按年龄过滤实现类
 * @Author sdzha
 * @Date 2020/12/23 14:49
 * @Version 1.0
 */
public class FilterEmpByAge implements MyPredicate<Emp> {
    @Override
    public boolean filter(Emp emp) {
        return emp.getAge() > 35;
    }
}
