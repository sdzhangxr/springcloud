package com.zhangxr.springcloud.entity;

import javax.persistence.*;

/**
 * @className Customer
 * @Description Jpa实体类
 * @Author sdzha
 * @Date 2020/12/19 20:02
 * @Version 1.0
 */
@Entity(name = "t_user")
public class User {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    @Column(length = 10)
    private int id;

    @Column(length = 10)
    private String name;

    @Column(length = 50)
    private String addr;

    public User() {
    }

    public User(int id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}




















