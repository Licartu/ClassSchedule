package com.whut.classschedule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.whut.classschedule.fragment.ClassFragment;
import com.whut.classschedule.fragment.JobFragment;
import com.whut.classschedule.fragment.MeFragment;

/**
 * 主页面底部菜单适配器
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    public MainFragmentAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int i){
        Fragment fragment=null;
        switch (i){
            case 0:
                fragment=new ClassFragment();
                break;
            case 1:
                fragment = new JobFragment();
                break;
            case 2:
                fragment = new MeFragment();
                break;
            default: break;
        }
        return fragment;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
