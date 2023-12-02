package com.funoi.service;

import android.content.Context;
import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.funoi.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class PullTest {
    private static final String Tag = "PullTest";
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void getStu() throws Throwable {
        InputStream is = Objects.requireNonNull(getClass().getClassLoader()).getResourceAsStream("test.xml");
        List<Student> students = Pull.getStu(is);
        for (Student student : students) {
            Log.i(Tag, student.toString());
            if (student.getName() == null)
                Log.i(Tag, "name:" + student.getName());
            if (student.getAge() == null)
                Log.i(Tag, "age:" + student.getAge());
        }
    }

    @Test
    public void saveStu() throws Throwable {
        List<Student> students = new ArrayList<>();
        List<String> hobby = new ArrayList<>();
        hobby.add("c++");
        hobby.add("python");
        students.add(new Student(1, "芙宁娜", 18, "女", hobby));
        Log.i(Tag, "students: 1  " + students);
        hobby = new ArrayList<>();
        hobby.add("c++");
        hobby.add("python");
        hobby.add("java");
        students.add(new Student(2, null, null, "女", hobby));
        Log.i(Tag, "students: 2  " + students);
        hobby = new ArrayList<>();
        hobby.add("c++");
        hobby.add("python");
        hobby.add("java");
        hobby.add("amd");
        students.add(new Student(null, null, 19, "女", hobby));
        students.add(new Student(null, null, null, null, null));
        Log.i(Tag, "students: 3  " + students);


        // 创建 .xml 文件
        File file = new File(context.getFilesDir(), "student.xml");
        Log.i(Tag, "dir: " + context.getFilesDir() + "  datadir: " + context.getDataDir());
        Log.d(Tag, "dir: " + file.getPath());

        FileOutputStream stream = context.openFileOutput("student.xml", Context.MODE_PRIVATE);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

        Pull.saveStu(students, writer);
        Log.i(Tag, "writer: " + writer);

//        InputStream is = getClass().getClassLoader().getResourceAsStream("test.xml");
        InputStream is=context.openFileInput("student.xml");
        Log.i(Tag,"is path: "+is.available());
        System.out.println(is);
        List<Student> s = Pull.getStu(is);
        System.out.println("------------");
        for (Student student : s) {
            Log.i(Tag, student.toString());
            if (student.getName() == null)
                Log.i(Tag, "name:" + student.getName());
            if (student.getAge() == null)
                Log.i(Tag, "age:" + student.getAge());
        }
    }
}
