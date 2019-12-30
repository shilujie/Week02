package com.bawei.week02.model.bean;
/*
 *@auther:史陆杰
 *@Date: 2019/12/30
 *@Time:15:09
 *@Description:${DESCRIPTION}
 **/


public class MyBean {
    String name;
    String age;

    public MyBean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
