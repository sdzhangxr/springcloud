package com.zhangxr.springcloud.lambda;

import java.util.Objects;

/**
 * @className Emp
 * @Description Lamdba表达式，员工对象
 * @Author sdzha
 * @Date 2020/12/23 14:25
 * @Version 1.0
 */
public class Emp {
    private int id;
    private String name;
    private int age;
    private Double salary;
    private Status status;

    public Emp() {
    }

    public Emp(int id) {
        this.id = id;
    }

    public Emp(int id, String name, int age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Emp(int id, String name, int age, Double salary, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return id == emp.id &&
                age == emp.age &&
                Objects.equals(name, emp.name) &&
                Objects.equals(salary, emp.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary);
    }

    public enum Status{
        FREE,
        BUSY,
        VOCATION,
    }
}
