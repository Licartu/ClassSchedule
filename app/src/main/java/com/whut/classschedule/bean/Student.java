package com.whut.classschedule.bean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019/5/6.
 */

public class Student extends LitePalSupport implements Serializable {
    private int Id;
    private List<Course> courseList=new ArrayList<>();
    private String studentName;
    private String password;
    private String tel;
    private String email;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Course> getCourseList() {
        return LitePal.where("student_id=?",String.valueOf(Id)).find(Course.class);
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student(int id, List<Course> courseList, String studentName, String password, String tel, String email) {

        Id = id;
        this.courseList = courseList;
        this.studentName = studentName;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }




}
