package com.funoi.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.funoi.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建 student 表的 service 类
 */
public class StuService {

    private final DBHelper dbHelper;

    public StuService(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void addStu(Student s) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // 写入的方式打开数据库

        // 查询数据库有没有相同 id 的学生
        if (findStu(s.getId()) != null) {
            return;
        }
        ContentValues stu = new ContentValues(); // 创建 ContentValue 对象
        // 将插入的参数放置在 stu 对象中
        stu.put("id", s.getId());
        stu.put("name", s.getName());
        if (s.getAge() != null) {
            stu.put("age", s.getAge());
        } else {
            stu.put("age", 0);
        }
        stu.put("sex", s.getSex());
        String hobby = s.getHobby().toString()
                .substring(1, s.getHobby().toString().length() - 1);
        stu.put("hobby", hobby);

        db.insert("student", null, stu); // 调用 db 操作对象将 stu 对象插入数据库
    }

    /**
     * 通过 id 查询学生
     *
     * @param id_ id
     * @return 查到的学生, 没找到返回 null
     */
    public Student findStu(Integer id_) {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // 只读的方式打开数据库

        Cursor cursor =
                db.query(
                        "student",
                        null,
                        "id=?",
                        new String[]{String.valueOf(id_)},
                        null,
                        null,
                        null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            String sex = cursor.getString(cursor.getColumnIndexOrThrow("sex"));
            String[] str = cursor.getString(cursor.getColumnIndexOrThrow("hobby"))
                    .split(", ");
            List<String> hobby = new ArrayList<>(Arrays.asList(str));

            cursor.close();
            return new Student(id, name, age, sex, hobby);
        } else {
            cursor.close();
            return null;
        }
    }

    public void delStu(Integer id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // 写入的方式打开数据库
        db.delete("student", "id=?",
                new String[]{String.valueOf(id)}); // 调用 db 操作对象将对应 id 的记录删除
    }

    /**
     * 更新学生
     *
     * @param s 将更新的数据封装成 Student 对象作为参数
     */
    public void updateStu(Student s) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // 写入的方式打开数据库

        // 查询数据库有没有相同 id 的学生
        if (findStu(s.getId()) != null) {
            return;
        }

        ContentValues stu = new ContentValues();
        if (s.getName() != null) {
            stu.put("name", s.getName());
        }
        if (s.getAge() != null) {
            stu.put("age", s.getAge());
        }
        if (s.getSex() != null) {
            stu.put("sex", s.getSex());
        }
        if (s.getHobby() != null) {
            String hobby = s.getHobby().toString()
                    .substring(1, s.getHobby().toString().length() - 1);
            stu.put("hobby", hobby);
        }

        db.update("student", stu, "id=?", new String[]{String.valueOf(s.getId())});
    }

    /**
     * 更新学生信息, 更新 id 的方法
     *
     * @param s      将更新的数据封装成 Student 对象作为参数
     * @param old_id 原来的 id
     */
    public void updateStu(Student s, Integer old_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // 写入的方式打开数据库

        // 查询数据库有没有相同 id 的学生
        if (findStu(s.getId()) != null) {
            return;
        }

        ContentValues stu = new ContentValues();
        if (s.getId() != null) {
            stu.put("id", s.getId());
        }
        if (s.getName() != null) {
            stu.put("name", s.getName());
        }
        if (s.getAge() != null) {
            stu.put("age", s.getAge());
        }
        if (s.getSex() != null) {
            stu.put("sex", s.getSex());
        }
        if (s.getHobby() != null) {
            String hobby =
                    s.getHobby().toString()
                            .substring(1, s.getHobby().toString().length() - 1);
            stu.put("hobby", hobby);
        }

        db.update("student", stu, "id=?", new String[]{String.valueOf(old_id)});
    }

    /**
     * 查询所有学生，分页查询
     *
     * @return 返回 Student 数组, 没有返回 null
     */
    public List<Student> findAll(Integer offset, Integer maxResult) {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // 只读的方式打开数据库

        List<Student> students = new ArrayList<>();

        Cursor cursor =
                db.query("student", null, null, null, null, null, null,
                        offset + " , " + maxResult);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            String sex = cursor.getString(cursor.getColumnIndexOrThrow("sex"));
            String[] str = cursor.getString(cursor.getColumnIndexOrThrow("hobby"))
                    .split(", ");
            List<String> hobby = new ArrayList<>(Arrays.asList(str));

            students.add(new Student(id, name, age, sex, hobby));
        }

        cursor.close();
        if (!students.isEmpty()) {
            return students;
        } else {
            return null;
        }
    }

    /**
     * 获取数据库中记录的数量
     */
    public Integer getCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // 只读的方式打开数据库
        Cursor cursor =
                db.query("student", new String[]{"count(*)"}, null, null, null, null,
                        null);

        cursor.moveToFirst();
        int sum = cursor.getInt(0);

        cursor.close();
        return sum;
    }
}
