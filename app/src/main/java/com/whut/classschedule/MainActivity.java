package com.whut.classschedule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

 import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.whut.classschedule.bean.Student;
import com.whut.classschedule.fragment.ClassFragment;
import com.whut.classschedule.fragment.JobFragment;
import com.whut.classschedule.fragment.MeFragment;

import java.util.ArrayList;

import butterknife.BindView;
 import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {
    public static FragmentPagerAdapter mPagerAdapter;



    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.mBottom) BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student_data");
        Log.e("获取",student.getStudentName());

        //设置点击监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    根据navagatin.xml中item的id进行case
                    case R.id.class_item:
                        //跳到对应ViewPager的page
                        viewPager.setCurrentItem(0);

                        break;
                    case R.id.job_item:
                        viewPager.setCurrentItem(1);

                        break;
                    case R.id.me_item:
                        viewPager.setCurrentItem(2);

                        break;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(0).getItemId());




        //底部导航栏有几项就有几个Fragment
        final ArrayList<Fragment> fgLists = new ArrayList<>(3);
        fgLists.add(new ClassFragment());
        fgLists.add(new JobFragment());
        fgLists.add(new MeFragment());

        //设置适配器用于装载Fragment
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private int mChildCount = 0;
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);  //得到Fragment
            }

            @Override
            public int getCount() {
                return fgLists.size();  //得到数量
            }
            @Override

            public void notifyDataSetChanged() {

                mChildCount = getCount();

                super.notifyDataSetChanged();

            }
            @Override
            public int getItemPosition(Object object) {
                if ( mChildCount > 0) {

                    mChildCount --;

                    return POSITION_NONE;

                }

                return super.getItemPosition(object);
            }
        };
        viewPager.setAdapter(mPagerAdapter);   //设置适配器
        viewPager.setOffscreenPageLimit(2); //预加载剩下两页
//ViewPager的监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPagerAdapter.notifyDataSetChanged();
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                //滑动页面后做的事，这里与BottomNavigationView结合，使其与正确page对应
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //以上就将Fragment装入了ViewPager
    }
    public Student getStudent(){
        return student;
    }
}
