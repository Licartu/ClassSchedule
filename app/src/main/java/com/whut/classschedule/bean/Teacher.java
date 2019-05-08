package com.whut.classschedule.bean;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019/5/6.
 */

public class Teacher extends LitePalSupport implements Serializable{
    private int Id;
    private String teacherName;
    private String tel;
    private String address;
    private String email;
    private List<Course> courseList=new ArrayList<>();

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Teacher(int id, String teacherName, String tel, String address, String email, List<Course> courseList) {

        Id = id;
        this.teacherName = teacherName;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.courseList = courseList;
    }
}