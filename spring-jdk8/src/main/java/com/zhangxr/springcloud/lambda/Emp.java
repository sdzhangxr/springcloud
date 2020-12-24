package com.zhangxr.springcloud.lambda;

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

    public Emp() {
    }

    public Emp(int id, String name, int age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
