package com.whut.classschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.whut.classschedule.bean.Course;
import com.whut.classschedule.bean.Exam;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyExamActivity extends BaseActivity {
    @BindView(R.id.iv_back3)ImageView imageView;
    @BindView(R.id.rv_exam)RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exam);
        ButterKnife.bind(this);
        List<Exam> allExamList=LitePal.findAll(Exam.class);
        List<Exam> examList=new ArrayList<>();
        List<Course> courseList= student.getCourse();
        Date date=new Date();
        if (courseList.size()!=0&&allExamList.size()!=0){
            for(Exam exam:allExamList){
                if(loopList(courseList,LitePal.find(Exam.class,exam.getId(),true).getCourse())&&exam.getTime().after(date)){
                    examList.add(exam);
                }
            }
        }



        //设置RecyclerView管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //初始化适配器
        MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter(examList);
        //设置添加或删除item时的动画，这里使用默认动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        recyclerView.setAdapter(mAdapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private boolean loopList(List<Course> courseList,Course course){
        boolean result=false;
        for (Course mCouse:courseList){
            if(mCouse.getId()==course.getId())
                result =true;
        }
        return result;
    }
}
