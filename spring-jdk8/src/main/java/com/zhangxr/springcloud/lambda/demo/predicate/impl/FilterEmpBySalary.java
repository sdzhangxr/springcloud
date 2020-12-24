package com.zhangxr.springcloud.lambda.demo.predicate.impl;

import com.zhangxr.springcloud.lambda.Emp;
import com.zhangxr.springcloud.lambda.demo.predicate.MyPredicate;

/**
 * @className FilterEmpBySalary
 * @Description TODO
 * @Author sdzha
 * @Date 2020/12/23 14:58
 * @Version 1.0
 */
public class FilterEmpBySalary implements MyPredicate<Emp> {
    @Override
    public boolean filter(Emp emp) {
        return emp.getSalary() > 4000;
    }
}
