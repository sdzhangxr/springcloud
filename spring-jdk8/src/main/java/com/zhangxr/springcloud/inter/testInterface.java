package com.zhangxr.springcloud.inter;

import com.zhangxr.springcloud.inter.impl.SubClass;

/**
 * @className testInterface
 * @Description
 *      SubClass继承MyClass类,实现MyFun接口,MyClass与MyFun中都存在方法getName,
 *      那么调用子类的getName方法会输出什么？
 *          1.类优先原则
 *      如果实现两个接口，并且默认方法完全相同，那么实现类再重写方法时要指明重写那个接口的方法
 *
 * @Author sdzha
 * @Date 2020/12/31 10:39
 * @Version 1.0
 */
public class testInterface {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        String name = subClass.getName();
        System.out.println(name);
        System.out.println(MyFun.show());
    }
}
