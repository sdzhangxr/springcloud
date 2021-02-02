package com.zhangxr.springcloud.service;

import com.zhangxr.springcloud.entities.TUser;

/**
 * @className UserService
 * @Description User接口
 * @Author sdzha
 * @Date 2021/1/29 15:04
 * @Version 1.0
 */
public interface UserService {

    int insertUser(TUser user);

    TUser getUserById(int id);

}
