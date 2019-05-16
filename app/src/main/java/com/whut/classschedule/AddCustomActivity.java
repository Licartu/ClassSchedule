package com.whut.classschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.whut.classschedule.bean.Course;
import com.whut.classschedule.bean.Custom;
import com.whut.classschedule.bean.Student;
import com.whut.classschedule.fragment.ClassFragment;

import org.litepal.LitePal;

import java.util.List;

public class AddCustomActivity extends AppCompatActivity {


    private Student student;
    private String className=null;
    private String teacherName=null;
    private String classroom=null;
    private String day=null;
    private String startWeek=null;
    private String endWeek=null;
    private String classTime=null;
    private String hour=null;
    private String tool=null;
    private int singleOrDouble=-1;
    private EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

         Intent intent=getIntent();
        student=(Student) intent.getSerializableExtra("student");
        Log.e("studentid",""+student.getId());


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.single_double_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                String result = radioButton.getText().toString();
                switch (result){
                    case "不区分":singleOrDouble=0;break;
                    case  "单周":singleOrDouble=1;break;
                    case  "双周":singleOrDouble=2;break;
                }
            }
        });


        Button button1=findViewById(R.id.btn_add);
        Button button2=findViewById(R.id.btn_cancel_add);
        editText1=(EditText)findViewById(R.id.et_classname);
        editText2=(EditText)findViewById(R.id.et_teachername);
        editText3=(EditText)findViewById(R.id.et_classroom);
        editText4=(EditText)findViewById(R.id.et_startweek);
        editText5=(EditText)findViewById(R.id.et_endweek);
        editText6=(EditText)findViewById(R.id.et_day);
        editText7=(EditText)findViewById(R.id.et_classtime);
        editText8=(EditText)findViewById(R.id.et_hour);
        editText9=(EditText)findViewById(R.id.et_tool);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                className=editText1.getText().toString();

                teacherName=editText2.getText().toString();

                classroom=editText3.getText().toString();

                startWeek=editText4.getText().toString();

                endWeek=editText5.getText().toString();

                day=editText6.getText().toString();

                classTime=editText7.getText().toString();

                hour=editText8.getText().toString();

                tool=editText9.getText().toString();


                if (ifInputTrue(className,startWeek,endWeek,singleOrDouble,day,classTime,hour)){
                    Custom custom=new Custom();
                    custom.setCustomName(className);
                    custom.setClassroom(classroom);
                    custom.setTeacherName(teacherName);
                    custom.setStartWeek(Integer.parseInt(startWeek));
                    custom.setEndWeek(Integer.parseInt(endWeek));
                    custom.setSingleOrDouble(singleOrDouble);
                    custom.setClassTime(Integer.parseInt(classTime));
                    custom.setHour(Integer.parseInt(hour));
                    custom.setDay(Integer.parseInt(day));
                    custom.setTool(tool);
                    custom.setStudent(LitePal.find(Student.class,student.getId()));
                    custom.save();


                    Log.e("新建",custom.getCustomName());
                    Toast.makeText(AddCustomActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent();
                    intent2.putExtra("createcustom",custom);
                    setResult(ClassFragment.ADD_CUSTOM,intent2);
                    finish();
                }

            }
        });

    }
    public boolean ifInputTrue( String className, String startWeek, String endWeek,int singleOrDouble,String day,String classTime,String hour){
        if(className.equals("")){
            Toast.makeText(AddCustomActivity.this,"添加失败，课程名称不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (startWeek.equals("")){
            Toast.makeText(AddCustomActivity.this,"添加失败，起始周不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (endWeek.equals("")){
            Toast.makeText(AddCustomActivity.this,"添加失败，结束周不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (singleOrDouble==-1){
            Toast.makeText(AddCustomActivity.this,"添加失败，必须选择单双周~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (day.equals("")){
            Toast.makeText(AddCustomActivity.this,"添加失败，星期不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (classTime.equals("")){
            Toast.makeText(AddCustomActivity.this,"添加失败，上课时间不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (hour.equals("")){
            Toast.makeText(AddCustomActivity.this,"添加失败，课时不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (Integer.parseInt(startWeek)<1||Integer.parseInt(startWeek)>20||Integer.parseInt(startWeek)>Integer.parseInt(endWeek)||
                Integer.parseInt(endWeek)<1||Integer.parseInt(endWeek)>20){
            Toast.makeText(AddCustomActivity.this,"添加失败，起始周或结束周必须在1-20之间，且起始周不大于结束周~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.parseInt(day)<1||Integer.parseInt(day)>7){
            Toast.makeText(AddCustomActivity.this,"添加失败，星期必须在1-7之间~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.parseInt(classTime)<1||Integer.parseInt(classTime)>10){
            Toast.makeText(AddCustomActivity.this,"添加失败，上课时间必须在1-10之间~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.parseInt(hour)<1||Integer.parseInt(hour)>(11-Integer.parseInt(classTime))){
            Toast.makeText(AddCustomActivity.this,"添加失败，如果你的上课时间为 "+classTime+",那么课程时必须在1-"+(11-Integer.parseInt(classTime))+"之间~",Toast.LENGTH_SHORT).show();
            return false;
        }
        List<Custom> customList= LitePal.where("student_id=? and day=?",String.valueOf(student.getId()),day).find(Custom.class);
        List<Course> courseList=student.getDayCourseList(Integer.parseInt(day));
        boolean flag=false;
        //查找在course表中有无时间冲突的课程
        if (courseList.size()==0)
            flag=true;
        else {
            for (Course course:courseList){
                if(singleOrDouble!=course.getSingleOrDouble()&&singleOrDouble!=0&&course.getSingleOrDouble()!=0){
                    flag=true;
                }
                else if (Integer.parseInt(classTime)>=course.getClassTime()+course.getHour()||Integer.parseInt(classTime)+Integer.parseInt(hour)<=course.getClassTime()){
                    flag=true;
                }
                else if (Integer.parseInt(endWeek)<course.getStartWeek()||Integer.parseInt(startWeek)>course.getEndWeek()){
                    flag=true;
                }

                else {
                    flag=false;
                    Toast.makeText(AddCustomActivity.this,"课程冲突，添加失败",Toast.LENGTH_SHORT).show();
                    return flag;
                }
            }
        }
        if(customList.size()==0)
            flag=true;
        else {
            for (Custom custom:customList){
                if(singleOrDouble!=custom.getSingleOrDouble()&&singleOrDouble!=0&&custom.getSingleOrDouble()!=0){
                    flag=true;
                }
                else if (Integer.parseInt(classTime)>=custom.getClassTime()+custom.getHour()||Integer.parseInt(classTime)+Integer.parseInt(hour)<=custom.getClassTime()){
                    flag=true;
                }
                else if (Integer.parseInt(endWeek)<custom.getStartWeek()||Integer.parseInt(startWeek)>custom.getEndWeek()){
                    flag=true;
                }
                else {
                    flag=false;
                    Toast.makeText(AddCustomActivity.this,"添加失败，与已存在课程冲突",Toast.LENGTH_SHORT).show();
                    return flag;
                }
            }
        }
        //查找在custom表中有无时间冲突的课程

        Log.e("flag",""+flag);
        return flag;
    }
}
