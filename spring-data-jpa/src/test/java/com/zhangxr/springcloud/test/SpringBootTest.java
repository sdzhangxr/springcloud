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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        /**
         *  查询的sql语句：
         *      select clazz0_.cid as cid1_0_, clazz0_.cname as cname2_0_ from t_clazz clazz0_
         *    可见并没有查询班级信息，这样属于懒加载查询。
         *  查询班级中的学生时：
         *      出现 no session 异常。
         *  原因：
         *      1.当查询班级信息结束，session关闭
         *      2.再次调用dao查询学生信息，就会出现no session
         *  解决：
         *      1.设置立即加载
         *        @OneToMany(mappedBy = "clazz",fetch = FetchType.EAGER)
         *          FetchType.EAGER  立即加载
         *          FetchType.LAZY   立即加载
         *       查询的sql
         *          Hibernate: select clazz0_.cid as cid1_0_, clazz0_.cname as cname2_0_ from t_clazz clazz0_
         *          Hibernate: select list0_.cid as cid3_1_0_, list0_.sid as sid1_1_0_, list0_.sid as sid1_1_1_, list0_.cid as cid3_1_1_, list0_.sname as sname2_1_1_ from t_student list0_ where list0_.cid=?
         *          Hibernate: select list0_.cid as cid3_1_0_, list0_.sid as sid1_1_0_, list0_.sid as sid1_1_1_, list0_.cid as cid3_1_1_, list0_.sname as sname2_1_1_ from t_student list0_ where list0_.cid=?
         *      2.延长session的生命周期
         *
         */
        for (Clazz clazz : list) {
            System.out.println("班级信息：" + clazz.getCid()+ "\t" + clazz.getCname());
            List<Student> list1 = clazz.getList();
            for (Student student : list1) {
                System.out.println("学生信息：" + student.getSid() + "\t" + student.getSname() + "\t");
            }
        }
    }

    /***
     * @Author sdzha
     * @Description 查询班级信息
     * @Date 2020/12/22 10:01
     * @Param []
     * @return void
     */
    @Test
    public void getStudentInfo(){
        //多对一：@ManyToOne 立即加载
        List<Student> list = studentDao.findAll();
        for (Student student : list) {
            System.out.println(student.getSid() + "\t" + student.getSname());
            Clazz clazz = student.getClazz();
            if (clazz != null) System.out.println(clazz.getCid() + "\t" + clazz.getCname());
            System.out.println("-----------------------");
        }
    }

    /***
     * @Author sdzha
     * @Description 删除班级
     * @Date 2020/12/22 10:58
     * @Param []
     * @return void
     */
    @Test
    public void delClazzInfo(){
        //前提：删除之前班级没有关联学生
        Clazz clazz = new Clazz();
        clazz.setCid(1);
        clazzDao.delete(clazz);
//        clazzDao.deleteById(1);

        /**
         * 删除之前班级有关联学生，会报错
         *  Cannot delete or update a parent row: a foreign key constraint fails (`jpa`.`t_student`, CONSTRAINT `FKdoqw5tbx714ymdae2apufdo1k` FOREIGN KEY (`cid`) REFERENCES `t_clazz` (`cid`))
         *  无法删除或更新父行：外键约束失败（`jpa`.`t_student`，约束`FKdoqw5tbx714ymdae2apufdo1k`外键（`cid`）引用`t_clazz`（`cid`）
         *  解决：
         *  1.级联删除
         *  @OneToMany(mappedBy = "clazz",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
         *      cascade = CascadeType.ALL       级联所有操作
         *      cascade = CascadeType.PERSIST   级联持久化操作
         *      cascade = CascadeType.MERGE     级联合并操作
         *      cascade = CascadeType.REMOVE    级联删除操作
         *      cascade = CascadeType.REFRESH   级联刷新操作
         *      cascade = CascadeType.DETACH    级联分离操作
         *  2.先断开主外键连接，在删除主表数据
         *
         */
    }

    /***
     * @Author sdzha
     * @Description 删除学生信息
     * @Date 2020/12/22 10:59
     * @Param []
     * @return void
     */
    @Test
    public void delStudentInfo(){
        Student student = new Student();
        student.setSid(2);
        studentDao.delete(student);
    }

    /**
     * @Author sdzha
     * @Description TODO
     * @Date 2020/12/22 11:19
     * @Param []
     * @return void
     */
    @Test
    public void addClazzAndStudentInfo(){
        //新建班级信息
        Clazz clazz = new Clazz();
        clazz.setCname("c++");
        //新建学生信息
        Student stu1 = new Student();
        stu1.setSname("李四");
        Student stu2 = new Student();
        stu2.setSname("王五");
        //绑定学生与班级关系
        stu1.setClazz(clazz);
        stu2.setClazz(clazz);

        //绑定班级与学生关系
        List<Student> list = new ArrayList<>();
        list.add(stu1);
        list.add(stu2);
        clazz.setList(list);

        //保存
        clazzDao.save(clazz);
    }

    //---------------------------------------一对多查询 end-----------------------------------------

    //---------------------------------------多对多查询 start-----------------------------------------














    //---------------------------------------多对多查询 end-----------------------------------------
}
















