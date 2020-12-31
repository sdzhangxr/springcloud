package com.zhangxr.springcloud.inter;

/**
 * @className MyFun
 * @Description 接口
 *      之前接口只能静态变量，方法不能有方法体
 * @Author sdzha
 * @Date 2020/12/31 10:27
 * @Version 1.0
 */
public interface MyFun {

    //默认方法
    default String getName(){
        return "zhangxr";
    }

    //静态方法
    public static String show(){
        return "show";
    };
}
