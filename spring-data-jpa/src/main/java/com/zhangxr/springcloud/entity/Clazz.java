package com.zhangxr.springcloud.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @className Clazz
 * @Description 一对多 班级表
 * @Author sdzha
 * @Date 2020/12/21 16:46
 * @Version 1.0
 */
@Entity(name = "t_clazz")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int cid;
    @Column(length = 20)
    private String cname;

    /**
     * 配置映射关系
     */
    @OneToMany(mappedBy = "clazz",fetch = FetchType.EAGER)
    private List<Student> list;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", list=" + list +
                '}';
    }
}
