package com.whut.classschedule;

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

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class AddCourseActivity extends AppCompatActivity {


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


        Button button=findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText1=(EditText)findViewById(R.id.et_classname);
                className=editText1.getText().toString();
                editText2=(EditText)findViewById(R.id.et_teachername);
                teacherName=editText2.getText().toString();
                editText3=(EditText)findViewById(R.id.et_classroom);
                classroom=editText3.getText().toString();
                editText4=(EditText)findViewById(R.id.et_startweek);
                startWeek=editText4.getText().toString();
                editText5=(EditText)findViewById(R.id.et_endweek);
                endWeek=editText5.getText().toString();
                editText6=(EditText)findViewById(R.id.et_day);
                day=editText6.getText().toString();
                editText7=(EditText)findViewById(R.id.et_classtime);
                classTime=editText7.getText().toString();
                editText8=(EditText)findViewById(R.id.et_hour);
                hour=editText8.getText().toString();
                editText9=(EditText)findViewById(R.id.et_tool);
                tool=editText9.getText().toString();

                List<Custom> customList= LitePal.where("student_id=? and day=?",String.valueOf(student.getId()),String.valueOf(day)).find(Custom.class);
                List<Course> courseList=LitePal.where("student_id=? and day=?",String.valueOf(student.getId()),String.valueOf(day)).find(Course.class);
                if (ifInputTrue(className,startWeek,endWeek,singleOrDouble,day,classTime,hour,customList,courseList)){
                    Toast.makeText(AddCourseActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public boolean ifInputTrue( String className, String startWeek, String endWeek,int singleOrDouble,String day,String classTime,String hour,List<Custom> customList,List<Course> courseList){
        if(className.equals("")){
            Toast.makeText(AddCourseActivity.this,"添加失败，课程名称不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (startWeek.equals("")){
            Toast.makeText(AddCourseActivity.this,"添加失败，起始周不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (endWeek.equals("")){
            Toast.makeText(AddCourseActivity.this,"添加失败，结束周不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (singleOrDouble==-1){
            Toast.makeText(AddCourseActivity.this,"添加失败，必须选择单双周~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (day.equals("")){
            Toast.makeText(AddCourseActivity.this,"添加失败，星期不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (classTime.equals("")){
            Toast.makeText(AddCourseActivity.this,"添加失败，上课时间不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (hour.equals("")){
            Toast.makeText(AddCourseActivity.this,"添加失败，课时不能为空~",Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (Integer.parseInt(startWeek)<1||Integer.parseInt(startWeek)>20||Integer.parseInt(startWeek)>Integer.parseInt(endWeek)||
                Integer.parseInt(endWeek)<1||Integer.parseInt(endWeek)>20){
            Toast.makeText(AddCourseActivity.this,"添加失败，起始周或结束周必须在1-20之间，且起始周不大于结束周~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.parseInt(day)<1||Integer.parseInt(day)>7){
            Toast.makeText(AddCourseActivity.this,"添加失败，星期必须在1-7之间~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.parseInt(classTime)<1||Integer.parseInt(classTime)>10){
            Toast.makeText(AddCourseActivity.this,"添加失败，上课时间必须在1-10之间~",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (Integer.parseInt(hour)<1||Integer.parseInt(hour)>(11-Integer.parseInt(classTime))){
            Toast.makeText(AddCourseActivity.this,"添加失败，如果你的上课时间为 "+classTime+",那么课程时必须在1-"+(11-Integer.parseInt(classTime))+"之间~",Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean flag=false;
        //查找在course表中有无时间冲突的课程
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
            else break;;
        }
        //查找在custom表中有无时间冲突的课程
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
            else break;;
        }
        return flag;
    }
}
