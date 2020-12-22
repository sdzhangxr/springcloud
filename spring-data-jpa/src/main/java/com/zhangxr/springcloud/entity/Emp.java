package com.zhangxr.springcloud.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @className Emp
 * @Description TODO
 * @Author sdzha
 * @Date 2020/12/22 14:01
 * @Version 1.0
 */
@Entity(name = "t_emp")
public class Emp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;

    @Column(length = 20)
    private String ename;
    //使Emp对象放弃对外键的维护
    @ManyToMany(mappedBy = "emps")
    private List<Project> projects = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
