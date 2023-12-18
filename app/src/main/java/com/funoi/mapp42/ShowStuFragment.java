package com.funoi.mapp42;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 显示查找学生结果的 fragment
 */
public class ShowStuFragment extends Fragment {

    private static final String TAG = "ShowStuFragment"; // 标志

    protected RecyclerView mRecView;
    protected RecyclerView.LayoutManager mLayoutManager;

    /**
     * 创建视图的方法
     *
     * @return fragment 要显示的视图
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 将 show_stu.xml 填充到 fragment 页面
        return inflater.inflate(R.layout.show_stu, container, false);
    }

    /**
     * 初始化方法，在 onCreateView 方法之后立即调用，可以在这里设置页面的数据什么的
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle saveInstanceState) {
        Log.d(TAG, "onViewCreated");

        mRecView = view.findViewById(R.id.recView);
    }
}
