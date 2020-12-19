package com.zhangxr.springcloud.test;

import com.zhangxr.springcloud.entity.Customer;
import com.zhangxr.springcloud.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @className JpaTest
 * @Description TODO
 * @Author sdzha
 * @Date 2020/12/18 14:33
 * @Version 1.0
 */
public class JpaTest {
    /***
     * @Author sdzha
     * @Description 测试Jpa保存数据
     * @Date 2020/12/18 15:45
     * @Param []
     * @return void
     */
    @Test
    public void testSave(){
        /**
         * 1.创建实体管理类工厂，借助Persistence的静态方法获取
         * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
         */
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("myJpa");
        //2.创建实体管理类
        EntityManager entityManager = factory.createEntityManager();
        //3.获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //4.开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        //5.保存操作
        entityManager.persist(customer);
        //6.提交事务
        transaction.commit();
        //7.释放资源
        entityManager.close();
        factory.close();
    }

    /***
     * @Author sdzha
     * @Description 测试 JpaUtils工具类
     * @Date 2020/12/18 15:45
     * @Param []
     * @return void
     */
    @Test
    public void testJpaUtils(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
    }
    /***
     * @Author sdzha
     * @Description 根据id查询客户
     *      使用find方法查询：
     *          1.查询的对象就是当前客户对象本身
     *          2.在调用find方法的时候，就会发送sql语句查询数据库
     *      立即加载
     * @Date 2020/12/18 15:44
     * @Param []
     * @return void
     */
    @Test
    public void testFind(){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try{
            //获取实体管理对象
            entityManager = JpaUtils.getEntityManager();
            //获取事务对象
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            /*
             * find : 根据id查询数据
             *      class：查询数据的结果需要包装的实体类类型的字节码
             *      id：查询的主键的取值
             */
            Customer customer = entityManager.find(Customer.class, 1L);
            //提交事务
            transaction.commit();
            //输出
            System.out.println(customer.toString());
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }

    }
    /***
     * @Author sdzha
     * @Description 根据id查询客户
     *  getReference方法
     *      1.获取的对象是一个动态代理对象
     *      2.调用getReference方法不会立即发送sql语句查询数据库
     *          调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *  延迟加载（懒加载）
     *      得到的是一个动态代理对象,什么时候用，什么使用才会查询
     * @Date 2020/12/18 16:00
     * @Param []
     * @return void
     */
    @Test
    public void testGetReference(){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try{
            //获取实体管理对象
            entityManager = JpaUtils.getEntityManager();
            //获取事务对象
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            //执行查询
            Customer customer = entityManager.getReference(Customer.class, 1L);
            //提交事务
            transaction.commit();
            //输出
            System.out.println(customer.toString());
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }

    }

    @Test
    public void testDelete(){

        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try{
            //获取实体管理对象
            entityManager = JpaUtils.getEntityManager();
            //获取事务对象
            transaction = entityManager.getTransaction();
            //开启事务
            transaction.begin();
            Customer customer = new Customer();
            customer.setCustId(2L);
            //执行删除
            entityManager.remove(customer);
            //提交事务
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }

    }
}
