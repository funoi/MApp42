package com.funoi.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private List<String> hobby;

    public Student(Integer id, String name, Integer age, String sex, List<String> hobby) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.hobby = hobby;
    }

    public Student() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", sex='" + sex
                + '\'' + ", hobby=" + hobby + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }
}
