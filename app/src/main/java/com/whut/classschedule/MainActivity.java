package com.whut.classschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
 import android.support.design.widget.Snackbar;
 import android.support.design.widget.TabLayout;
 import android.support.v4.view.PagerAdapter;
 import android.support.v4.view.ViewPager;
 import android.support.v7.app.AppCompatActivity;
 import android.view.KeyEvent;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.widget.ImageView;
 import android.widget.TextView;
 import com.whut.classschedule.adapter.MainFragmentAdapter;
import com.whut.classschedule.fragment.MyDialogFragment;

import butterknife.BindView;
 import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    /**
     * 菜单标题
     */
    private final int[] TAB_TITLES = new int[]{R.string.menu_class, R.string.menu_job,  R.string.menu_me};
    /**
     * 菜单图标
     */
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_main_class_selector, R.drawable.tab_main_job_selector,  R.drawable.tab_main_me_selector};

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    /**
     * 页卡适配器
     */
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 初始化页卡
        initPager();

        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMGS);
    }

    /**
     * 设置页卡显示效果
     * @param tabLayout
     * @param inflater
     * @param tabTitles
     * @param tabImgs
     */
    private void setTabs(TabLayout tabLayout,LayoutInflater inflater,int [] tabTitles,int [] tabImgs){
        for (int i=0;i<tabImgs.length;i++){
            TabLayout.Tab tab=tabLayout.newTab();
            View view=inflater.inflate(R.layout.item_main_menu,null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitles[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);
        }
    }
    private void initPager(){
        adapter=new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // 关联切换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) { }
            @Override public void onTabReselected(TabLayout.Tab tab) { }

        });

    }
}
