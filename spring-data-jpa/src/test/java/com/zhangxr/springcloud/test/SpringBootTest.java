package com.zhangxr.springcloud.test;

import com.zhangxr.springcloud.dao.ClazzDao;
import com.zhangxr.springcloud.dao.StudentDao;
import com.zhangxr.springcloud.dao.UserDao;
import com.zhangxr.springcloud.entity.Clazz;
import com.zhangxr.springcloud.entity.Student;
import com.zhangxr.springcloud.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @className SpringBootTest
 * @Description TODO
 * @Author sdzha
 * @Date 2020/12/19 20:23
 * @Version 1.0
 */
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {
    @Test
    public void testCreate(){
        System.out.println("创建表");
    }

    @Autowired
    private UserDao userDao;

    //---------------------------------------单表查询 start-----------------------------------------
    /***
     * @Author sdzha
     * @Description 测试添加
     *      如果指定id，会根据id执行查询，查到执行update，查不到执行insert
     *      不指定id，直接执行insert
     * @Date 2020/12/21 14:24
     * @Param
     * @return
     */
    @Test
    public void addUser(){
        System.out.println("开始添加用户");
        User user = new User();
        //user.setId(2);
        user.setName("赵六");
        user.setAddr("天津");
        userDao.save(user);
        System.out.println("保存成功");
    }

    /***
     * @Author sdzha
     * @Description 根据id查询
     * @Date 2020/12/21 14:27
     * @Param []
     * @return void
     */
    @Test
    public void findUserById(){
        Optional<User> optional = userDao.findById(1);
        User user = optional.get();
        System.out.println(user);
    }

    /***
     * @Author sdzha
     * @Description 查询全部
     * @Date 2020/12/21 14:34
     * @Param []
     * @return void
     */
    @Test
    public void findUserAll(){
        //不指定参数
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println("不指定参数" + user);
        }
        //排序字段
        List<User> list1 = userDao.findAll(Sort.by("id"));
        for (User user : list) {
            System.out.println("排序字段" + user);
        }
    }
    /***
     * @Author sdzha
     * @Description 分页查询
     *          Pageable：配置分页查询信息
     *          of静态方法
     *          参数一： 查询第几页，如果查询第一页，参数为0
     *          参数二：每页几条数据
     *          参数三：排序方式
     *          参数四：排序字段
     * @Date 2020/12/21 14:53
     * @Param []
     * @return void
     */
    @Test
    public void findUserPage(){
        Pageable pageable = PageRequest.of(0, 1,
                Sort.Direction.DESC, "id");
        Page<User> page = userDao.findAll(pageable);
        for (User user : page) {
            System.out.println(user);
        }
    }

    /***
     * @Author sdzha
     * @Description 删除
     *      现根据id查询，再删除
     * @Date 2020/12/21 14:59
     * @Param []
     * @return void
     */
    @Test
    public void delUser(){
        User user = new User();
        user.setId(2);
        userDao.delete(user);
//        userDao.deleteById(2);
    }

    /***
     * @Author sdzha
     * @Description 自定义查询
     * @Date 2020/12/21 15:24
     * @Param []
     * @return void
     */
    @Test
    public void findUserByName(){
        //按照name查
        List<User> list = userDao.findByName("张三");
        for (User user : list) {
            System.out.println(user);
        }
        //按照addr查
        List<User> list1 = userDao.findByAddr("山东");
        for (User user : list1) {
            System.out.println(user);
        }
        //按照name与addr查
        List<User> list2 = userDao.findByNameAndAddr("张三","山东");
        for (User user : list2) {
            System.out.println(user);
        }
        //查询id在指定区间，并且按照id倒序
        List<User> list3 = userDao.findByIdBetweenOrderByIdDesc(1,5);
        for (User user : list3) {
            System.out.println(user);
        }
    }

    /***
     * @Author sdzha
     * @Description Jpql查询 查询所有
     * @Date 2020/12/21 15:59
     * @Param []
     * @return void
     */
    @Test
    public void loadUserList(){
        List<User> list = userDao.loadUserList();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void loadNameList(){
        List<Object[]> list = userDao.loadNameList();
        for (Object[] user : list) {
            System.out.println(Arrays.toString(user));
        }
    }
    @Test
    public void loadToUserList(){
        List<User> list = userDao.loadToUserList();
        for (User user : list) {
            System.out.println(user);
        }
    }

    //---------------------------------------单表查询 end-----------------------------------------

    //---------------------------------------一对多查询 start-----------------------------------------

    @Autowired
    private ClazzDao clazzDao;
    @Autowired
    private StudentDao studentDao;
    /***
     * @Author sdzha
     * @Description 添加班级数据
     * @Date 2020/12/21 17:10
     * @Param []
     * @return void
     */
    @Test
    public void addClazz(){
        Clazz clazz = new Clazz();
        clazz.setCname("c++");
        clazzDao.save(clazz);
    }
    /***
     * @Author sdzha
     * @Description 添加学生信息
     * @Date 2020/12/21 17:14
     * @Param []
     * @return void
     */
    @Test
    public void addStudent(){
//        添加学生，但是没有所属的班级
//        Student student = new Student();
//        student.setSname("张三");
//        studentDao.save(student);

//        添加学生，指定所属的班级
        Student student = new Student();
        student.setSname("李四");

        Clazz clazz = new Clazz();
        clazz.setCid(1);
        student.setClazz(clazz);

        studentDao.save(student);
    }

    /**
     * @Author sdzha
     * @Description 修改班级信息
     * @Date 2020/12/21 17:24
     * @Param []
     * @return void
     */
    @Test
    public void updateClazz(){
        Clazz clazz = new Clazz();
        clazz.setCid(2);
        clazz.setCname("c");
        clazzDao.save(clazz);
    }
    /**
     * @Author sdzha
     * @Description 修改学生信息
     * @Date 2020/12/21 17:28
     * @Param []
     * @return void
     */
    @Test
    public void updateStudent(){
        Student student = new Student();
        student.setSid(2);
        student.setSname("lisi");
        Clazz clazz = new Clazz();
        clazz.setCid(2);
        student.setClazz(clazz);
        studentDao.save(student);
    }


    /***
     * @Author sdzha
     * @Description 查询班级信息
     * @Date 2020/12/21 17:45
     * @Param
     * @return
     */
    @Test
    public void getClazzInfo(){
        List<Clazz> list = clazzDao.findAll();
        for (Clazz clazz : list) {
            System.out.println(clazz.getCid()+ "\t" + clazz.getCname());
        }
    }
}
















