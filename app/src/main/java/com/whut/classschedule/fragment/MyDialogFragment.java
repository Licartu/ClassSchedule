package com.whut.classschedule.fragment;


import android.app.Dialog;
import android.app.DialogFragment;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends android.support.v4.app.DialogFragment {

    private View rootView;
    private String courseName,teacherName,weekTime,classTime,classRoom;
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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_dialog, null);
        Button button=rootView.findViewById(R.id.bt_delete);
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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft=getFragmentManager().beginTransaction();
//                ft.remove(this);
                ft.addToBackStack(null);
                new OtherDialogFragment().show(getFragmentManager(), "delete_sure");
            }
        });

        return rootView;

    }
    public static MyDialogFragment newInstance(String courseName,String teacherName,String weekTime,String classTime,String classroom){
        MyDialogFragment fragment=new MyDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("coursename",courseName);
        bundle.putString("teachername",teacherName);
        bundle.putString("weektime",weekTime);
        bundle.putString("classtime",classTime);
        bundle.putString("classroom",classroom);
        fragment.setArguments(bundle);
        return fragment;
    }

}
