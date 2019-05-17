package com.whut.classschedule;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whut.classschedule.bean.Student;
import com.whut.classschedule.fragment.ResumeDialogFragment;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.tv_getid)TextView studentId;
    @BindView(R.id.et_oldpassword)EditText et_oldPassword;
    @BindView(R.id.et_newpassword)EditText et_newPassword;
    @BindView(R.id.et_check)EditText et_check;
    @BindView(R.id.iv_back)ImageView iv_back;
    @BindView(R.id.bt_change)Button bt_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        studentId.setText(String.valueOf(student.getId()));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword=et_oldPassword.getText().toString();
                String newPassword=et_newPassword.getText().toString();
                String check=et_check.getText().toString();
                if(ifInputRight(oldPassword,newPassword,check)){
                    ContentValues values = new ContentValues();
                    values.put("password",newPassword);
                    LitePal.update(Student.class,values,student.getId());
//                    student.setPassword(newPassword);
//                    student.update(student.getId());
                    Toast.makeText(ChangePasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    Log.e("密码修改为",student.getPassword());
                    new ResumeDialogFragment().show(getSupportFragmentManager(),"resume");

                }
            }
        });


    }

    private boolean ifInputRight(String oldPassword, String newPassword, String check) {
        if (oldPassword.equals("")|newPassword.equals("")|check.equals("")){
            Toast.makeText(ChangePasswordActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (oldPassword.length()<6|oldPassword.length()>16|newPassword.length()<6|newPassword.length()>16|check.length()<6|check.length()>16){
            Toast.makeText(ChangePasswordActivity.this,"输入必须为6-16之间的数字字母组合",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!oldPassword.equals(student.getPassword())){
            Toast.makeText(ChangePasswordActivity.this,"旧密码输入错误",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (oldPassword.equals(newPassword)){
            Toast.makeText(ChangePasswordActivity.this,"新密码不能与旧密码一样",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!newPassword.equals(check)){
            Toast.makeText(ChangePasswordActivity.this,"新密码与确认密码匹配错误",Toast.LENGTH_SHORT).show();
            return false;
        }
         else return true;

    }
}
