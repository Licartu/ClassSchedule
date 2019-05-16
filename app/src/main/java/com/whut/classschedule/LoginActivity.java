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


import com.whut.classschedule.bean.Student;


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
                        finish();
                    }

                }
                }

        });
    }
}
