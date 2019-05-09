package com.whut.classschedule.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.whut.classschedule.R;

import com.whut.classschedule.bean.Classroom;
import com.whut.classschedule.bean.Course;
import com.whut.classschedule.bean.Student;
import com.whut.classschedule.bean.Teacher;


import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;



/**
 * Created by Lenovo on 2019/4/30.
 */

public class ClassFragment extends Fragment {

    protected static final int REUEST_CODDE = 0;
    private SQLiteDatabase db;
    //星期几
    private RelativeLayout day;
    private Student student;
    public ClassFragment(){

    }
//    @Override
//    public void oncreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        //注册广播
//        registerReceiver();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_class, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //LitePal 获取实例
        db = Connector.getDatabase();
        loadData(student);
    }
    //从数据库中取数据
    private void loadData(Student student) {
        //课程列表
        List<Course> coursesList=student.getCourseList();

        //使用从数据库读取出来的课程信息来加载课程表视图
        for (Course course:coursesList){

            createItemCourseView(course);
        }
    }

    //创建单个课程视图
    private void createItemCourseView(final Course course) {
        int getDay=course.getDay();
        if (getDay<1||getDay>7||course.getStartWeek()<1||course.getEndWeek()>20||course.getEndWeek()<course.getStartWeek())
            Toast.makeText(getActivity(),"课表包含错误信息",Toast.LENGTH_LONG).show();
        else {
            int dayId=0;
            switch (getDay){
                case 1:dayId=R.id.monday;break;
                case 2:dayId=R.id.tuesday;break;
                case 3:dayId=R.id.wednesday;break;
                case 4:dayId=R.id.thursday;break;
                case 5:dayId=R.id.friday;break;
                case 6:dayId=R.id.saturday;break;
                case 7:dayId=R.id.sunday;break;
            }
            day = getView().findViewById(dayId);


            Thread thread=new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            day.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            int height=day.getHeight();
                                            final View v = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
                                            v.setY(height*(course.getClassTime()-1));//设置开始高度,即第几节课开始
                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                                                    (ViewGroup.LayoutParams.MATCH_PARENT,course.getHour()*height - 8); //设置布局高度,即跨多少节课
                                            v.setLayoutParams(params);
                                            TextView text = v.findViewById(R.id.text_view);
                                            text.setText(course.getClassName()+"\n"+course.getClassroom().getName());//显示课程信息
                                            day.addView(v);
                                            int vid=v.getId();

                                            //设置点击监听
                                            v.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    int endTime = course.getClassTime() + (course.getHour() - 1);
                                                    int vid=v.getId();
                                                    MyDialogFragment myDialogFragment = MyDialogFragment.newInstance(course.getClassName(), course.getTeacher().getTeacherName(),
                                                            "第" + course.getStartWeek() + "-" + course.getEndWeek() + "周", "第" + course.getClassTime() + "-" + endTime + "节", course.getClassroom().getName(),course.getId(),vid);
                                                    myDialogFragment.setTargetFragment(ClassFragment.this,REUEST_CODDE);
                                                    myDialogFragment.show(getFragmentManager(), "course");


                                                }
                                            });
                                        }
                                    }
                            );
                        }
                    }
            );


        }
    }
    public void deleteCourse(View v,int course_id){

            v.setVisibility(View.GONE);//先隐藏
            day.removeView(v);//再移除课程视图
            LitePal.delete(Course.class,course_id);
        Toast.makeText(getActivity(),"课程已删除",Toast.LENGTH_LONG).show();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==REUEST_CODDE){
            try {
                int viewId=Integer.parseInt(data.getStringExtra("courseviewid"));
                int courseId=Integer.parseInt(data.getStringExtra("classid"));
                deleteCourse(getView().findViewById(viewId),courseId);
            } catch (NumberFormatException e) {

                e.printStackTrace();

            }

        }
    }
//保存数据到数据库
    private void saveData(Student student,  String className, String teacherName,String classroom,int day,int startWeek,
                          int endWeek,int classTime,int hour,String tool,int singleOrDouble){
        Course course=new Course();
        course.setClassName(className);
        course.getTeacher().setTeacherName(teacherName);
        course.getClassroom().setName(className);
        course.setDay(day);
        course.setStartWeek(startWeek);
        course.setEndWeek(endWeek);
        course.setClassTime(classTime);
        course.setHour(hour);
        course.setTool(tool);
        course.setSingleOrDouble(singleOrDouble);
        course.save();
        List<Course> courseList=student.getCourseList();
        courseList.add(course);
        student.save();
    }


}