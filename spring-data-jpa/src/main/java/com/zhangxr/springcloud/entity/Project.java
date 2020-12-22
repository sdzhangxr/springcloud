package com.zhangxr.springcloud.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @className Project
 * @Description TODO
 * @Author sdzha
 * @Date 2020/12/22 14:03
 * @Version 1.0
 */
@Entity(name = "t_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;

    @Column(length = 20)
    private String pname;

    /**
     * @JoinTable 描述当前实体类与第三张表的关联关系
     *
     */
    @ManyToMany
    @JoinTable(
        name = "emp_pro",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"pid","eid"})},
        joinColumns = {@JoinColumn(name = "pid",referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "eid",referencedColumnName = "id")}
    )
    private List<Emp> emps = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }
}
