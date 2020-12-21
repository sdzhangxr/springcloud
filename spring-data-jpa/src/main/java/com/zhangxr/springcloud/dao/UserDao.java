package com.zhangxr.springcloud.dao;

import com.zhangxr.springcloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @className UserDao
 * @Description TODO
 *      JpaRepository<T, ID>  Jpa提供的接口：定义了实体的基本操作
 *          T 制定具体操作的实体类
 *          ID 当前实体类的主键类型
 *
 * @Author sdzha
 * @Date 2020/12/21 14:07
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 自定义查询：JpaRepository提供的方法不能满足业务需求
     *  方法名命名规范
     *      findByxxx findBy固定开始 xxx属性名 findByName 按照name属性查询
     *
     */

    List<User> findByName(String name);

    List<User> findByAddr(String addr);

    /**
     * 多条件联合查
     */
    List<User> findByNameAndAddr(String name,String addr);

    /**
     * 查询id在指定区间，并且按照id倒序
     */
    List<User> findByIdBetweenOrderByIdDesc(Integer minId,Integer maxId);


    /**
     * jpql查询
     */
    //@Query(value = "from com.zhangxr.springcloud.entity.User")
    @Query(value = "select user from com.zhangxr.springcloud.entity.User user")
    List<User> loadUserList();

    /***
     * 返回的数据不会封装到User实体类中
     */
    @Query(value = "select user.id,user.name,user.addr from com.zhangxr.springcloud.entity.User user")
    List<Object[]> loadNameList();

    /**
     * 把返回的数据封装到User实体类中
     */
    @Query(value = "select new com.zhangxr.springcloud.entity.User(id,name,addr) " +
            "from com.zhangxr.springcloud.entity.User user")
    List<User> loadToUserList();
}
