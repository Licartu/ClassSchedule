package com.whut.classschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInfoActivity extends BaseActivity {
    @BindView(R.id.tv_studentname)TextView textView1;
    @BindView(R.id.tv_studentid)TextView textView2;
    @BindView(R.id.tv_studenttype)TextView textView3;
    @BindView(R.id.tv_studentsex)TextView textView4;
    @BindView(R.id.tv_studentage)TextView textView5;
    @BindView(R.id.tv_major)TextView textView6;
    @BindView(R.id.tv_grade)TextView textView7;
    @BindView(R.id.tv_studenttel)TextView textView8;
    @BindView(R.id.tv_studentemail)TextView textView9;
    @BindView(R.id.iv_back2)ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        textView1.setText(student.getStudentName());
        textView2.setText(String.valueOf(student.getId()));
        textView3.setText(student.getType());
        textView4.setText(student.getSex());
        textView5.setText(String.valueOf(student.getAge()));
        textView6.setText(student.getMajor());
        textView7.setText(student.getGrade());
        textView8.setText(student.getTel());
        textView9.setText(student.getEmail());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
