package com.funoi.mapp42;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.funoi.model.Student;
import com.funoi.service.Pull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加学生的 fragment 页面.
 */
public class AddStuFragment extends Fragment {

    private static final String TAG = "AddStuFragment"; // 标志

    /**
     * 创建视图的方法.
     *
     * @return fragment 要显示的视图
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        // 将 add_stu.xml 填充到 fragment 页面
        return inflater.inflate(R.layout.add_stu, container, false);
    }

    /**
     * 初始化方法，在 onCreateView 方法之后立即调用，可以在这里设置页面的数据什么的
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState) {
        Log.d(TAG, "onViewCreated");
        Button save = view.findViewById(R.id.save_button);
        setSaveButtonListener(save, view);
    }

    public void setSaveButtonListener(Button button, View v) {
        button.setOnClickListener(
                view -> {
                    Student student = getInputStu(v);
                    List<Student> students;

                    File file = new File(requireActivity().getFilesDir(), "students.xml");
                    if (!file.exists()) {
                        try {
                            requireActivity().openFileOutput("students.xml", Context.MODE_PRIVATE);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // 读取 .xml 文件内容
                    InputStream is;
                    try {
                        is = requireActivity().openFileInput("students.xml");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        students = Pull.getStu(is);
                        Log.i(TAG, "read " + students.size() + " from xml");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    students.add(student);

                    // 保存到 .xml 文件
                    FileOutputStream os;
                    try {
                        os = requireActivity().openFileOutput("students.xml", Context.MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

                    try {
                        Pull.saveStu(students, writer);
                        Log.i(TAG, "save " + students.size() + " to xml");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    Toast.makeText(getContext(), "保存成功", Toast.LENGTH_LONG).show();
                });
    }

    public Student getInputStu(View v) {
        Integer id;
        if (!((EditText) v.findViewById(R.id.input_user_id)).getText().toString().isEmpty()) {
            id =
                    Integer.valueOf(
                            ((EditText) v.findViewById(R.id.input_user_id)).getText().toString());
        } else {
            id = null;
        }

        String name;
        if (!((EditText) v.findViewById(R.id.input_user_name)).getText().toString().isEmpty()) {
            name = ((EditText) v.findViewById(R.id.input_user_name)).getText().toString();
        } else {
            name = null;
        }

        Integer age;
        if (!((EditText) v.findViewById(R.id.input_user_age)).getText().toString().isEmpty()) {
            age = Integer.valueOf(
                    ((EditText) v.findViewById(R.id.input_user_age)).getText().toString());
        } else {
            age = null;
        }

        RadioGroup sexRg = v.findViewById(R.id.input_user_sex);
        String sex =
                ((RadioButton) v.findViewById(sexRg.getCheckedRadioButtonId())).getText().toString();

        List<String> hobby = new ArrayList<>();
        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(v.findViewById(R.id.input_user_hobby_1));
        checkBoxes.add(v.findViewById(R.id.input_user_hobby_2));
        checkBoxes.add(v.findViewById(R.id.input_user_hobby_3));
        checkBoxes.add(v.findViewById(R.id.input_user_hobby_4));
        checkBoxes.add(v.findViewById(R.id.input_user_hobby_5));
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isChecked() && (i != checkBoxes.size() - 1)) {
                hobby.add(checkBoxes.get(i).getText().toString());
            } else if (checkBoxes.get(i).isChecked() && (i == checkBoxes.size() - 1)) {
                String str =
                        ((EditText) v.findViewById(R.id.input_user_hobby_5_text)).getText().toString();
                if (!str.isEmpty()) {
                    hobby.add(str);
                }
            }
        }
        if (!hobby.isEmpty()) {
            return new Student(id, name, age, sex, hobby);
        } else {
            return new Student(id, name, age, sex, null);
        }
    }
}
