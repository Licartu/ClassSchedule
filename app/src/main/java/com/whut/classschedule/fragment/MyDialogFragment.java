package com.whut.classschedule.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
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
    private String className,teacherName,weekTime,classTime,classRoom;
    private int classId,classViewId,courseFlag;
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


        className=bundle.getString("classname");
        teacherName=bundle.getString("teachername");
        weekTime=bundle.getString("weektime");
        classTime=bundle.getString("classtime");
        classRoom=bundle.getString("classroom");
        classId=bundle.getInt("classid");
        classViewId=bundle.getInt("classviewid");
        courseFlag=bundle.getInt("courseflag");
        Log.e("flag", ""+courseFlag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_dialog, null);
        Button btDelete=rootView.findViewById(R.id.bt_delete);
        Button btCancel=rootView.findViewById(R.id.bt_cancel);
        TextView tv1=rootView.findViewById(R.id.tv_class);
        TextView tv2=rootView.findViewById(R.id.tv_teacher);
        TextView tv3=rootView.findViewById(R.id.tv_week);
        TextView tv4=rootView.findViewById(R.id.tv_time);
        TextView tv5=rootView.findViewById(R.id.tv_classroom);

        //如果是course，不显示删除按钮，是custom显示删除按钮
        if (courseFlag==0)
            btDelete.setVisibility(View.VISIBLE);
        else btDelete.setVisibility(View.GONE);

        tv1.setText(className);
        tv2.setText(teacherName);
        tv3.setText(weekTime);
        tv4.setText(classTime);
        tv5.setText(classRoom);

//删除课程
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(String.valueOf(classViewId),String.valueOf(classId));
                getDialog().dismiss();
            }
        });
//取消返回
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return rootView;
    }
    public static MyDialogFragment newInstance(String className,String teacherName,String weekTime,String classTime,String classroom,int classId,int classViewId,int courseFlag){
        MyDialogFragment fragment=new MyDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("classname",className);
        bundle.putString("teachername",teacherName);
        bundle.putString("weektime",weekTime);
        bundle.putString("classtime",classTime);
        bundle.putString("classroom",classroom);
        bundle.putInt("classid",classId);
        bundle.putInt("classviewid",classViewId);
        bundle.putInt("courseflag",courseFlag);
        fragment.setArguments(bundle);
        return fragment;
    }
    protected void setData(String classViewId,String classId) {
        if(getTargetFragment()==null)
        {
            return ;
        }
        Intent intent=new Intent();
        intent.putExtra("classviewid",classViewId);
        intent.putExtra("classid",classId);
        //获得目标Fragment,并将数据通过onActivityResult放入到intent中进行传值
        getTargetFragment().onActivityResult(ClassFragment.REUEST_CODDE, Activity.RESULT_OK, intent);

    }

}
