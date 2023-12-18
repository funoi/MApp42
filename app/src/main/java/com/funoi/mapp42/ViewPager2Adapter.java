package com.funoi.mapp42;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager2 的适配器，用来创建 viewpager2 的显示的视图
 */
public class ViewPager2Adapter extends FragmentStateAdapter {

    private static final String TAG = "viewPager2Adapter"; // 标志

    // 存储 viewpager2 的 fragment 页面
    private List<Fragment> fragments;

    /**
     * 构造方法，在这里设置要创建哪些页面
     *
     * @param fragmentActivity FragmentActivity 的对象，在 activity 里调用该方法时传入 "this"
     */
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragments = new ArrayList<>();
        this.fragments.add(new AddStuFragment());
        this.fragments.add(new ShowStuFragment());
    }

    /**
     * 切换 fragment 页面时调用，根据传进来的索引决定显示哪个 fragment 页面
     *
     * @param position fragment 索引
     * @return 要显示的 fragment 页面
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    /**
     * 获取 viewpager2 的页面的数量
     *
     * @return viewpager2 的页面的数量
     */
    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
