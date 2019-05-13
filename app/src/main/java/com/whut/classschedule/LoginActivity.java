package com.whut.classschedule;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whut.classschedule.bean.Classroom;
import com.whut.classschedule.bean.Course;
import com.whut.classschedule.bean.Custom;
import com.whut.classschedule.bean.Student;
import com.whut.classschedule.bean.Teacher;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    static Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SQLiteDatabase db = Connector.getDatabase();

//        Course course1=new Course();
//        Student student1=new Student();
//        Custom custom1=new Custom();
//        Classroom classroom=new Classroom();
//        Teacher teacher1=new Teacher();
//
//        course1.setCourseName("大学物理");
//        course1.setSingleOrDouble(0);
//        course1.setStartWeek(1);
//        course1.setEndWeek(18);
//        course1.setClassTime(2);
//        course1.setHour(1);
//        course1.setDay(2);
//        course1.setTool("大物书");
//        course1.setTeacher(teacher1);
//        course1.setClassroom(classroom);
//
//
//
//
//        teacher1.setTeacherName("陈强");
//        teacher1.setEmail("1111111");
//        teacher1.setAddress("理工");
//        teacher1.setTel("151515");
//        teacher1.getCourseList().add(course1);
//
//
//        classroom.setName("新1-101");
//        classroom.getCourseList().add(course1);
//
//
//        custom1.setCustomName("约会");
//        course1.setSingleOrDouble(1);
//        custom1.setStartWeek(1);
//        custom1.setEndWeek(1);
//        custom1.setClassTime(5);
//        custom1.setHour(1);
//        custom1.setDay(2);
//        custom1.setTool("大物书");
//        custom1.setClassroom("宾馆");
//        custom1.setTeacherName("杨超越");
//        custom1.setStudent(student1);
//
//
//
//
//
//        student1.setStudentName("李豪");
//        student1.setPassword("123456");
//        student1.setEmail("123456789");
//        student1.setTel("123");
//        student1.getCourseList().add(course1);
//        student1.getCustomList().add(custom1);
//
//        course1.getStudentList().add(student1);
//        course1.save();
//        teacher1.save();
//        classroom.save();
//        custom1.save();
//        student1.save();


        Button btn=findViewById(R.id.bt_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1=findViewById(R.id.et_account);
                String account=editText1.getText().toString();
                EditText editText=findViewById(R.id.et_password);
                String password=editText.getText().toString();
                if(account.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                }
                else if (password.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }
                else {
                    List<Student> studentList1=LitePal.where("id=? and password=?",account,password).find(Student.class);
                    if(studentList1.size()==0) {
                        Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_LONG).show();
                    }
                    else{
                        student=studentList1.get(0);
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("student_data",student);
                        Log.e("传输","student");
                        startActivity(intent);
                    }

                }
                }

        });
    }
}
