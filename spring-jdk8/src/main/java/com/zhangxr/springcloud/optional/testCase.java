package com.zhangxr.springcloud.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @className testCase
 * @Description Optional案例
 * @Author sdzha
 * @Date 2020/12/31 9:33
 * @Version 1.0
 */
public class testCase {

    @Test
    public void testMan(){
        Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println(godnessName);
    }

    //传统处理空指针异常
    public String getGodnessName(Man man){
        if (man != null){
            Godness godness = man.getGodness();
            if (godness != null){
                String name = godness.getName();
                return name;
            }
        }
        return null;
    }
    @Test
    public void testNewMan(){
        NewMan newMan = new NewMan();
        String godnessNameByOptional = getGodnessNameByOptional(Optional.of(newMan));
        System.out.println(godnessNameByOptional);
    }

    public String getGodnessNameByOptional(Optional<NewMan> man){
        return man.orElse(new NewMan())
                .getOptionalGodness()
                .orElse(new Godness())
                .getName();
    }
}

class Man{
    private Godness godness;

    public Man() {
    }

    public Man(Godness godness) {
        this.godness = godness;
    }

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
class NewMan{
    private Optional<Godness> optionalGodness = Optional.empty();

    public NewMan() {
    }

    public NewMan(Optional<Godness> optionalGodness) {
        this.optionalGodness = optionalGodness;
    }

    public Optional<Godness> getOptionalGodness() {
        return optionalGodness;
    }

    public void setOptionalGodness(Optional<Godness> optionalGodness) {
        this.optionalGodness = optionalGodness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "optionalGodness=" + optionalGodness +
                '}';
    }
}
class Godness{
    private String name;

    public Godness() {
    }

    public Godness(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Godness{" +
                "name='" + name + '\'' +
                '}';
    }
}
