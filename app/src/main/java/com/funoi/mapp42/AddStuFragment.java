package com.funoi.mapp42;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * 添加学生的 fragment 页面
 */
public class AddStuFragment extends Fragment {
    private static final String TAG="AddStuFragment";  // 标志

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState){
        // 将 add_stu.xml 填充到 fragment 页面
        return inflater.inflate(R.layout.add_stu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState){
        Log.d(TAG,"onViewCreated");

    }
}
