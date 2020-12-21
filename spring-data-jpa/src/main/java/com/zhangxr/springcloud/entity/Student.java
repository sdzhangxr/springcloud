package com.zhangxr.springcloud.entity;

import javax.persistence.*;

/**
 * @className Student
 * @Description 一对多 学生表
 * @Author sdzha
 * @Date 2020/12/21 16:48
 * @Version 1.0
 */
@Entity(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int sid;
    @Column(length = 20)
    private String sname;

    /**
     * 配置映射关系
     */
    @ManyToOne
    @JoinColumn(name = "cid")
    private Clazz clazz;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }


}
