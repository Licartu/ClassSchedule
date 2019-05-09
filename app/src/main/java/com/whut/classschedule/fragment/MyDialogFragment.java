package com.whut.classschedule.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.whut.classschedule.R;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends android.support.v4.app.DialogFragment {

    private View rootView;
    private String courseName,teacherName,weekTime,classTime,classRoom;
    private int classId,courseViewId;
    public MyDialogFragment() {
        // Required empty public constructor
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog =getDialog();
        if(dialog != null){
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            dialog.getWindow().setLayout((int) (displayMetrics.widthPixels *0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();


        courseName=bundle.getString("coursename");
        teacherName=bundle.getString("teachername");
        weekTime=bundle.getString("weektime");
        classTime=bundle.getString("classtime");
        classRoom=bundle.getString("classroom");
        classId=bundle.getInt("classid");
        courseViewId=bundle.getInt("courseviewid");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_dialog, null);
        Button bt_delete=rootView.findViewById(R.id.bt_delete);
        Button bt_cancel=rootView.findViewById(R.id.bt_cancel);
        TextView tv1=(TextView) rootView.findViewById(R.id.tv_class);
        TextView tv2=rootView.findViewById(R.id.tv_teacher);
        TextView tv3=rootView.findViewById(R.id.tv_week);
        TextView tv4=rootView.findViewById(R.id.tv_time);
        TextView tv5=rootView.findViewById(R.id.tv_classroom);

        tv1.setText(courseName);
        tv2.setText(teacherName);
        tv3.setText(weekTime);
        tv4.setText(classTime);
        tv5.setText(classRoom);

//删除课程
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(String.valueOf(courseViewId),String.valueOf(classId));

            }
        });
//取消返回
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return rootView;
    }
    public static MyDialogFragment newInstance(String courseName,String teacherName,String weekTime,String classTime,String classroom,int classId,int courseViewId){
        MyDialogFragment fragment=new MyDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("coursename",courseName);
        bundle.putString("teachername",teacherName);
        bundle.putString("weektime",weekTime);
        bundle.putString("classtime",classTime);
        bundle.putString("classroom",classroom);
        bundle.putInt("classid",classId);
        bundle.putInt("courseviewid",courseViewId);
        fragment.setArguments(bundle);
        return fragment;
    }
    protected void setData(String courseViewId,String classId) {
        if(getTargetFragment()==null)
        {
            return ;
        }
        Intent intent=new Intent();
        intent.putExtra("courseviewid",courseViewId);
        intent.putExtra("classid",classId);
        //获得目标Fragment,并将数据通过onActivityResult放入到intent中进行传值
        getTargetFragment().onActivityResult(ClassFragment.REUEST_CODDE, Activity.RESULT_OK, intent);

    }

}
