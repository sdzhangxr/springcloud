package com.zhangxr.springcloud.util.cache.mapper;

import com.zhangxr.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @className UserMapper
 * @Description 用户操作Mapper
 * @Author sdzha
 * @Date 2021/1/21 17:59
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    @Select("select * from t_user where id = #{id}")
    User getUserInfo(@Param("id") int id);
}
