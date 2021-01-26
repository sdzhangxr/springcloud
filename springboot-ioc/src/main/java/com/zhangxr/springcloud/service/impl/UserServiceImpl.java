package com.zhangxr.springcloud.service.impl;

import com.zhangxr.springcloud.entities.User;
import com.zhangxr.springcloud.mapper.UserMapper;
import com.zhangxr.springcloud.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className UserServiceImpl
 * @Description 用户操作接口实现类
 * @Author sdzha
 * @Date 2021/1/21 17:58
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }

}
