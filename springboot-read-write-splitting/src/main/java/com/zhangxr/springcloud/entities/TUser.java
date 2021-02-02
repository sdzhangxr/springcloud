package com.zhangxr.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className TUser
 * @Description 用户实体类
 * @Author sdzha
 * @Date 2021/1/29 16:12
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TUser {
    private int id;
    private String name;
    private int age;
}
