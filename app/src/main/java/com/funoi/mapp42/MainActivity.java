package com.funoi.mapp42;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.default_font_size); // 设置默认字体大小
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });

        // tab 标签
        String[] tabMenu = {"XML保存", "XML读取", "添加", "查找"};

        // 初始化控件
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        TabLayout tabLayout = findViewById(R.id.tabMenu);

        // 创建 viewpager2 适配器对象
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(viewPager2Adapter); // 给 viewpager2 设置适配器

        // 将 tab 标签与 viewpager2 绑定
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(tabMenu[position]))
                .attach();
    }
}
