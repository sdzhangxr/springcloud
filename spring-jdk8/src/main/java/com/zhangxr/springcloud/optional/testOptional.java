package com.zhangxr.springcloud.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @className testOptional
 * @Description Optional容器类常用方法
 * @Author sdzha
 * @Date 2020/12/30 15:57
 * @Version 1.0
 */
public class testOptional {
    /**
     * 1. Optional.of(T t); 创建一个Optional实例
     * 2. Optional.empty(); 创建一个空的Optional实例
     * 3. Optional.ofNullable(T t); 若t不为null,创建Optional实例
     * 4. isPresent(); 判断是否包含值
     * 5. orElse(T t); 如果调用对象包含值,返回该值.否则返回t.
     * 6. orElseGet(Supplier s); 如果调用对象包含值,返回该值.否则返回s获取的值.
     * 7. map(Function f); 如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty();
     * 8. flatMap(Function mapper); 与map相似，要求返回值必须是Optional
     */

    // 1. Optional.of(T t); 创建一个Optional实例
    @Test
    public void testOf(){
        Optional<User> op = Optional.of(new User());
        User user = op.get();
        System.out.println(user);
    }

    //2. Optional.empty(); 创建一个空的Optional实例
    @Test
    public void testEmpty(){
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());
    }

    //3. Optional.ofNullable(T t); 若t不为null,创建Optional实例
    @Test
    public void testOfNullable(){
        Optional<User> optional = Optional.ofNullable(new User());
        System.out.println(optional.get());
    }

    //4. isPresent(); 判断是否包含值
    @Test
    public void testIsPresent(){
        Optional<User> user = Optional.ofNullable(new User());
//        Optional<User> user = Optional.ofNullable(null);
        if (user.isPresent()){
            System.out.println(user.get());
        }
    }

    //5. orElse(T t); 如果调用对象包含值,返回该值.否则返回t.
    @Test
    public void testOrElse(){
        Optional<User> optional = Optional.ofNullable(new User());
        User user = optional.orElse(new User(1, "张三"));
        System.out.println(user);//User{id=0, name='null'}

        Optional<User> optional1 = Optional.ofNullable(null);
        User user1 = optional1.orElse(new User(1, "张三"));
        System.out.println(user1);//User{id=1, name='张三'}
    }

    //6. orElseGet(Supplier s); 如果调用对象包含值,返回该值.否则返回s获取的值.
    @Test
    public void testOrElseGet(){
        Optional<User> optional = Optional.ofNullable(null);
        User orElseGet = optional.orElseGet(() -> {
            User user = new User();
            user.setId(1);
            user.setName("张三");
            return user;
        });
        System.out.println(orElseGet);
    }

    //7. map(Function f); 如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty();
    @Test
    public void testMap(){
        Optional<User> optional = Optional.ofNullable(new User(1,"张三"));
        Optional<String> name = optional.map((e) -> {
            return e.getName();
        });
        System.out.println(name.get());
    }

    //8. flatMap(Function mapper); 与map相似，要求返回值必须是Optional
    @Test
    public void test(){
        Optional<User> optional = Optional.ofNullable(new User(1,"张三"));
        Optional<String> s = optional.flatMap((e) -> Optional.ofNullable(e.getName()));
        System.out.println(s.get());
    }

}
class User{
    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
