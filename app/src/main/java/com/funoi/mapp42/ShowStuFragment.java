package com.funoi.mapp42;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * 显示查找学生结果的 fragment
 */
public class ShowStuFragment extends Fragment {
    private static final String TAG = "ShowStuFragment";  // 标志

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ShowStuFragment() {
    }

    public static ShowStuFragment newInstance(String param1, String param2) {
        ShowStuFragment fragment = new ShowStuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 将 show_stu.xml 填充到 fragment 页面
        return inflater.inflate(R.layout.show_stu, container, false);
    }
}