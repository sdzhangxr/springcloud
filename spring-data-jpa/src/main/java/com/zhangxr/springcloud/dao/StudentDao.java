package com.zhangxr.springcloud.dao;

import com.zhangxr.springcloud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className StudentDao
 * @Description 一对多 学生接口
 * @Author sdzha
 * @Date 2020/12/21 17:08
 * @Version 1.0
 */
public interface StudentDao extends JpaRepository<Student,Integer> {
}
