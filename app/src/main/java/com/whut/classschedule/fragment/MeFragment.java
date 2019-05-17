package com.whut.classschedule.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whut.classschedule.ChangePasswordActivity;
import com.whut.classschedule.HelpActivity;
import com.whut.classschedule.MainActivity;
import com.whut.classschedule.ManageInforemActivity;
import com.whut.classschedule.MyExamActivity;
import com.whut.classschedule.MyInfoActivity;
import com.whut.classschedule.R;
import com.whut.classschedule.bean.Student;

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

    private Student student;
    @Override public void onAttach(Context context){
        super.onAttach(context);
        student=((MainActivity)context).getStudent();}
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
        textView.setText(student.getStudentName());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyInfoActivity.class);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyExamActivity.class);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ManageInforemActivity.class);
                startActivity(intent);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new  ResumeDialogFragment().show(getFragmentManager(),"edit");
            }
        });
    }

}
