package com.zhangxr.springcloud.dao;

import com.zhangxr.springcloud.entities.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className UserDAO
 * @Description User持久层
 * @Author sdzha
 * @Date 2021/1/29 15:16
 * @Version 1.0
 */
@Mapper
public interface UserDAO {

    int insertUser(TUser user);

    TUser getUserById(int id);
}
