package com.zhangxr.springcloud.dao;

import com.zhangxr.springcloud.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className ClazzDao
 * @Description 一对多 班级接口
 * @Author sdzha
 * @Date 2020/12/21 17:07
 * @Version 1.0
 */
public interface ClazzDao extends JpaRepository<Clazz,Integer> {
}
