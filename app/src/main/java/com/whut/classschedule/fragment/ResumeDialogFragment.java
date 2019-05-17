package com.whut.classschedule.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.whut.classschedule.LoginActivity;
import com.whut.classschedule.MyApplication;
import com.whut.classschedule.R;

/**
 * Created by Lenovo on 2019/5/18.
 */

public class ResumeDialogFragment extends DialogFragment {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getTag().equals("resume")) setCancelable(false);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_resume_dialog, null);
        getDialog().setTitle("提示");
        Button button1=view.findViewById(R.id.bt_resume);
        Button button2=view.findViewById(R.id.bt_cancelout);
        TextView textView=view.findViewById(R.id.tv_hint);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().exitAllActivity();
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
        if (getTag().equals("edit")){
            button2.setVisibility(View.VISIBLE);
            textView.setText("确定退出账号吗？");
        }
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
