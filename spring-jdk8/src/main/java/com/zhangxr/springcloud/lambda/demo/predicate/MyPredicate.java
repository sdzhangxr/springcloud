package com.zhangxr.springcloud.lambda.demo.predicate;

/**
 * @className MyPredicate
 * @Description 自定义过滤器接口
 * @Author sdzha
 * @Date 2020/12/23 14:47
 * @Version 1.0
 */
@FunctionalInterface
public interface MyPredicate<T> {
    /**
     * @Author sdzha
     * @Description 过滤器
     * @Date 2020/12/24 9:31
     * @Param [t, obj] [需过滤对象,过滤条件值]
     * @return boolean
     */
    boolean filter(T t);
}
