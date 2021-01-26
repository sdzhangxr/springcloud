package com.zhangxr.springcloud.service;

import com.zhangxr.springcloud.entities.User;

/**
 * @className UserService
 * @Description 用户操作接口
 * @Author sdzha
 * @Date 2021/1/21 17:56
 * @Version 1.0
 */
public interface UserService {

    User getUserInfo(int id);

}
