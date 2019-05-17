package com.whut.classschedule.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.whut.classschedule.AddCustomActivity;
import com.whut.classschedule.MainActivity;
import com.whut.classschedule.R;

import com.whut.classschedule.bean.Course;
import com.whut.classschedule.bean.Custom;
import com.whut.classschedule.bean.Student;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Lenovo on 2019/4/30.
 */

public class ClassFragment extends Fragment {
    public static int week=1;

    public static final int REUEST_CODDE = 0;
    public static final int ADD_CUSTOM=1;

    //星期几
//    private RelativeLayout day;
    private LinearLayout linearLayout;
    private Student student;
    private TextView textView;
    private ImageView imageView;
    public ClassFragment(){

    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        student=((MainActivity)context).getStudent();

        Log.e("收到",student.getStudentName()+student.getCourse().get(0).getCourseName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_class, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        textView=getView().findViewById(R.id.week);
        imageView=getView().findViewById(R.id.add_bt);
        textView.setText("第"+week+"周");
        linearLayout=getView().findViewById(R.id.pickweek);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView();

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddCustomActivity.class);
                intent.putExtra("student",student);
                startActivityForResult(intent,ADD_CUSTOM);
            }
        });

        loadData(student);
    }
    //从数据库中取数据
    private void loadData(Student student) {
        //课程列表
        List<Course> coursesList=student.getCourse();
        List<Custom> customList=student.getCustom();
        //使用从数据库读取出来的课程信息来加载课程表视图
        for (Course course:coursesList){
            int singleOrDouble=course.getSingleOrDouble();
            if (course.getStartWeek()<=week&&course.getEndWeek()>=week){
                if (singleOrDouble==0)
                    createItemCourseView(course);
                else if (singleOrDouble==1){
                    if (week%2==1)
                        createItemCourseView(course);
                }
                else {
                    if (week%2==0)
                        createItemCourseView(course);
                }
            }
        }
        for (Custom custom:customList){
            int singleOrDouble=custom.getSingleOrDouble();
            if (custom.getStartWeek()<=week&&custom.getEndWeek()>=week){
                if (singleOrDouble==0)
                    createItemCustomView(custom);
                else if (singleOrDouble==1){
                    if (week%2==1)
                        createItemCustomView(custom);
                }
                else {
                    if (week%2==0)
                        createItemCustomView(custom);
                }
            }
        }
    }


    //创建单个课程视图
    private void createItemCourseView( Course course) {
        final List<String> courseInfo=new ArrayList<>();
        courseInfo.add(String.valueOf(course.getId()));
        courseInfo.add(course.getCourseName());
        courseInfo.add(String.valueOf(course.getStartWeek()));
        courseInfo.add(String.valueOf(course.getEndWeek()));
        courseInfo.add(String.valueOf(course.getClassTime()));
        courseInfo.add(String.valueOf(course.getHour()));
        final RelativeLayout day;

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
            Log.e("id",dayId+"");


            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            day.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            int height=day.getHeight();

                                             View v = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
                                            v.setId(View.generateViewId());
                                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                                                    (ViewGroup.LayoutParams.MATCH_PARENT,Integer.parseInt(courseInfo.get(5))*height/10); //设置布局高度,即跨多少节课
                                            params.topMargin=height*(Integer.parseInt(courseInfo.get(4))-1)/10;
                                            TextView text = v.findViewById(R.id.text_view);
                                            String classroomName=LitePal.find(Course.class,Integer.parseInt(courseInfo.get(0)),true).getClassroom().getName();
                                            text.setText(courseInfo.get(1)+"\n"+classroomName);//显示课程信息
                                            day.addView(v,params);


                                            //设置点击监听
                                            v.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    int endTime = Integer.parseInt(courseInfo.get(4)) + (Integer.parseInt(courseInfo.get(5)) - 1);
                                                    int vid=v.getId();
                                                    int courseFlag=1;//是course为1，是custom为0
                                                    ClassDialogFragment classDialogFragment = ClassDialogFragment.newInstance(courseInfo.get(1), LitePal.find(Course.class,Integer.parseInt(courseInfo.get(0)),true).getTeacher().getTeacherName(),
                                                            "第" + courseInfo.get(2) + "-" + courseInfo.get(3) + "周", "第" + courseInfo.get(4) + "-" + endTime + "节", LitePal.find(Course.class,Integer.parseInt(courseInfo.get(0)),true).getClassroom().getName(),Integer.parseInt(courseInfo.get(0)),vid,courseFlag);
//                                                    classDialogFragment.setTargetFragment(ClassFragment.this,REUEST_CODDE);
                                                    classDialogFragment.show(getFragmentManager(), "course");


                                                }
                                            });
                                        }
                                    }
                            );
                        }
                    }
            ).start();


        }
    }
    //创建自定义课程视图
    private void createItemCustomView( Custom custom) {
        final List<String> customInfo=new ArrayList<>();
        customInfo.add(String.valueOf(custom.getId()));
        customInfo.add(custom.getCustomName());
        customInfo.add(String.valueOf(custom.getStartWeek()));
        customInfo.add(String.valueOf(custom.getEndWeek()));
        customInfo.add(String.valueOf(custom.getClassTime()));
        customInfo.add(String.valueOf(custom.getHour()));
        customInfo.add(custom.getClassroom());
        customInfo.add(custom.getTeacherName());
        int getDay = custom.getDay();
         final RelativeLayout day;
//        if (getDay < 1 || getDay > 7 || custom.getStartWeek() < 1 || custom.getEndWeek() > 20 || custom.getEndWeek() < custom.getStartWeek())
//            Toast.makeText(getActivity(), "课表包含错误信息", Toast.LENGTH_SHORT).show();
//        else {
            int dayId = 0;
            switch (getDay) {
                case 1:
                    dayId = R.id.monday;
                    break;
                case 2:
                    dayId = R.id.tuesday;
                    break;
                case 3:
                    dayId = R.id.wednesday;
                    break;
                case 4:
                    dayId = R.id.thursday;
                    break;
                case 5:
                    dayId = R.id.friday;
                    break;
                case 6:
                    dayId = R.id.saturday;
                    break;
                case 7:
                    dayId = R.id.sunday;
                    break;
            }
            day = getView().findViewById(dayId);
            Log.e("id",dayId+"");
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            day.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            int height=day.getHeight();

                                            View v = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
                                            v.setId(View.generateViewId());
                                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                                                    (ViewGroup.LayoutParams.MATCH_PARENT,Integer.parseInt(customInfo.get(5))*height/10); //设置布局高度,即跨多少节课
                                            params.topMargin=height*(Integer.parseInt(customInfo.get(4))-1)/10;
                                            TextView text = v.findViewById(R.id.text_view);
                                            text.setText(customInfo.get(1)+"\n"+customInfo.get(6));//显示课程信息
                                            day.addView(v,params);


                                            //设置点击监听
                                            v.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    int endTime = Integer.parseInt(customInfo.get(4)) + Integer.parseInt(customInfo.get(5)) - 1;
                                                    int vid=v.getId();
                                                    Log.e("vid",""+vid);
                                                    int courseFlag=0;
                                                    ClassDialogFragment classDialogFragment2 = ClassDialogFragment.newInstance(customInfo.get(1), customInfo.get(7),
                                                            "第" + customInfo.get(2) + "-" + customInfo.get(3) + "周", "第" + customInfo.get(4)
                                                                    + "-" + endTime + "节",customInfo.get(6),Integer.parseInt(customInfo.get(0)),vid,courseFlag);
                                                    classDialogFragment2.setTargetFragment(ClassFragment.this,REUEST_CODDE);
                                                    classDialogFragment2.show(getFragmentManager(), "custom");


                                                }
                                            });
                                        }
                                    }
                            );
                        }
                    }
            ).start();
//        }
    }
    public void deleteCourse(View v,int custom_id){

            v.setVisibility(View.GONE);//先隐藏
            RelativeLayout day=(RelativeLayout) v.getParent();
            day.removeView(v);//再移除课程视图
            LitePal.delete(Custom.class,custom_id);
            Toast.makeText(getActivity(),"课程已删除",Toast.LENGTH_SHORT).show();





    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case REUEST_CODDE:
                try {
                    int viewId=Integer.parseInt(data.getStringExtra("classviewid"));
                    int customId=Integer.parseInt(data.getStringExtra("classid"));
                    deleteCourse( getView().findViewById(viewId),customId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            case ADD_CUSTOM:
                try {
                    Custom custom=(Custom) data.getSerializableExtra("createcustom");
                    int singleOrDouble=custom.getSingleOrDouble();
                    if (custom.getStartWeek()<=week&&custom.getEndWeek()>=week){
                        if (singleOrDouble==0)
                            createItemCustomView(custom);
                        else if (singleOrDouble==1){
                            if (week%2==1)
                                createItemCustomView(custom);
                        }
                        else {
                            if (week%2==0)
                                createItemCustomView(custom);
                        }
                    }
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }




        }
    }
//保存数据到数据库
    private void saveData(Student student,  String className, String teacherName,String classroom,int day,int startWeek,
                          int endWeek,int classTime,int hour,String tool,int singleOrDouble){
        Course course=new Course();
        course.setCourseName(className);
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
//弹出周数选择框
    private void showPickerView(){
        final List<String> listWeek=getWeek();
        OptionsPickerView pvOptions=new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textView.setText(listWeek.get(options1));
                week=options1+1;
                MainActivity.mPagerAdapter.notifyDataSetChanged();



            }
        }).setSelectOptions(0).setOutSideCancelable(true).build();
        pvOptions.setPicker(listWeek);
        pvOptions.show();
    }

    private List<String> getWeek(){
        List<String> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            int j=i+1;
            list.add("第"+j+"周");
        }
        return list;
    }
//    @Override
//    public void onHiddenChange(boolean hidden){
//        super.onHiddenChanged(hidden);
//        if (!)
//    }
}