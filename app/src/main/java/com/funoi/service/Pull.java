package com.funoi.service;

import android.util.Xml;

import com.funoi.model.Student;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Pull {
    public static List<Student> getStu(InputStream is) throws Exception {
        List<Student> students = null;
        Student student = null;
        List<String> hobby = null;

        // 创建 pull 方式的解析器
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");

        // 获取标签类型
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    students = new ArrayList<>();
                    break;

                case XmlPullParser.START_TAG:
                    String name = parser.getName();  // 获取标签的名称

                    if ("student".equals(name)) {
                        student = new Student();
                        if (parser.getAttributeCount() != 0)  // 检查是否有属性
                            student.setId(Integer.valueOf(parser.getAttributeValue(0)));
                        break;
                    }

                    if (student != null) {
                        if ("name".equals(name)) {
                            student.setName(parser.nextText());
                            break;
                        }

                        if ("age".equals(name)) {
                            student.setAge(Integer.valueOf(parser.nextText()));
                            break;
                        }


                        if ("sex".equals(name)) {
                            student.setSex(parser.nextText());
                            break;
                        }


                        if ("hobbyArr".equals(name)) {
                            hobby = new ArrayList<>();
                            break;
                        }

                        if (hobby != null) {
                            if ("hobby".equals(name)) {
                                hobby.add(parser.nextText());
                                break;
                            }
                        }
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if ("hobbyArr".equals(parser.getName())) {
                        if (student != null) {
                            student.setHobby(hobby);
                        }
                        hobby = null;
                        break;
                    }

                    if ("student".equals(parser.getName())) {
                        if (students != null) {
                            students.add(student);
                        }
                        student = null;
                        break;
                    }

                    break;
            }
            eventType = parser.next();  // 读取下一行
        }

        return students;
    }

    public static void saveStu(List<Student> students, Writer writer) throws Exception {
        // 创建xml的生成器
        XmlSerializer serializer = Xml.newSerializer();  // pull 方式的 xml 序列话对象
        serializer.setOutput(writer);  // 设置输出流,字符方式
        serializer.startDocument("utf-8", true);

        serializer.startTag(null, "students");
        // 遍历链表
        for (Student student : students) {
            serializer.startTag(null, "student");
            if (student.getId() != null)
                serializer.attribute(null, "id", String.valueOf(student.getId()));

            if (student.getName() != null) {
                serializer.startTag(null, "name");
                serializer.text(student.getName());
                serializer.endTag(null, "name");
            }

            if (student.getAge() != null) {
                serializer.startTag(null, "age");
                serializer.text(String.valueOf(student.getAge()));
                serializer.endTag(null, "age");
            }

            if (student.getSex() != null) {
                serializer.startTag(null, "sex");
                serializer.text(student.getSex());
                serializer.endTag(null, "sex");
            }

            if (student.getHobby() != null) {
                serializer.startTag(null, "hobbies");

                for (String hobby : student.getHobby()) {
                    serializer.startTag(null, "hobby");
                    serializer.text(hobby);
                    serializer.endTag(null, "hobby");
                }
                serializer.endTag(null, "hobbies");
            }

            serializer.endTag(null, "student");
        }
        serializer.endTag(null, "students");

        serializer.endDocument();

        writer.flush();
        writer.close();
    }
}
