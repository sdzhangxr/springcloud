package com.zhangxr.springcloud.lambda.application.function;

/**
 * @className MyFunction
 * @Description 函数接口
 * @Author sdzha
 * @Date 2020/12/24 16:13
 * @Version 1.0
 */
@FunctionalInterface
public interface MyFunction {

    public Integer getValue(Integer value);

}
