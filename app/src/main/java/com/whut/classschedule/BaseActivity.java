package com.whut.classschedule;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.whut.classschedule.bean.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo on 2019/5/17.
 */

public class BaseActivity extends AppCompatActivity {
    public static Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }

}
