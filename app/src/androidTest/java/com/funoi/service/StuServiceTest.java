package com.funoi.service;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.funoi.model.Student;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class StuServiceTest {

    Context context = InstrumentationRegistry.getInstrumentation()
            .getTargetContext();
    StuService service = new StuService(context);

    @Test
    public void delStu() {
//        addStu();
        service.delStu(100);
    }

    @Test
    public void updateStu() {
        addStu();

        List<String> hobby = new ArrayList<>();
        hobby.add("c++");
        hobby.add("python");
        hobby.add("java");

        Student s = new Student(1, "funoi", 19, "女", hobby);
        Student s1 = new Student(2, "funoi", 20, "女", hobby);

        hobby.add("go");
        Student s2 = new Student(5, "funoi", null, null, hobby);

        service.updateStu(s);
        service.updateStu(s1);
        service.updateStu(s2, 1);

        service.updateStu(s2, 3);

    }

    @Test
    public void addStu() {
        List<String> hobby = new ArrayList<>();
        hobby.add("c++");
        hobby.add("python");
        hobby.add("java");
        hobby.add("rust");
        hobby.add("mysql");

        Student s = new Student(1, "fu", null, "女", hobby);
        Student s1 = new Student(2, "fu", 18, "女", hobby);
        Student s2 = new Student(4, "fu", 17, null, hobby);
        service.addStu(s);
        service.addStu(s1);
        service.addStu(s2);
    }

    @Test
    public void findStu() {
        addStu();
        Student s = service.findStu(1);
        System.out.println("s: " + s);
    }

    @Test
    public void findAll() {
//        addStu();

        Student s = service.findStu(1);
        System.out.println("s: " + s);
        System.out.println("getCount: " + service.getCount());
        List<Student> students = service.findAll(0, service.getCount());
        if (students != null) {
            for (Student student : students) {
                System.out.println("ss: " + student);
            }
        }
        System.out.println("list: " + students);

    }
}
