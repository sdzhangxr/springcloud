package com.zhangxr.springcloud.service.impl;

import com.zhangxr.springcloud.dao.UserDAO;
import com.zhangxr.springcloud.entities.TUser;
import com.zhangxr.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @className UserServiceImpl
 * @Description User接口实现类
 * @Author sdzha
 * @Date 2021/1/29 15:05
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Transactional
    @Override
    public int insertUser(TUser user) {
        int i = userDAO.insertUser(user);
        return user.getId();
    }

    @Override
    public TUser getUserById(int id) {
        TUser user = userDAO.getUserById(id);
        return user;
    }


}
