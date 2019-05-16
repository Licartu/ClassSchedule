package com.whut.classschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whut.classschedule.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 我
 */

public class MeFragment extends Fragment {
    @BindView(R.id.myinfo)LinearLayout linearLayout;
    @BindView(R.id.my_exam)LinearLayout linearLayout2;
    @BindView(R.id.manage_inform)LinearLayout linearLayout3;
    @BindView(R.id.change_password)LinearLayout linearLayout4;
    @BindView(R.id.help)LinearLayout linearLayout5;
    @BindView(R.id.exit)LinearLayout linearLayout6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ImageView imageView=getView().findViewById(R.id.iv_head);
        TextView textView=getView().findViewById(R.id.tv_username);
    }

}
