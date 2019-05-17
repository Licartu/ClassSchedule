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
    private int id;
    private List<Course> courseList=new ArrayList<>();
    private List<Custom> customList=new ArrayList<>();
    private String studentName;
    private String password;
    private String tel;
    private String email;
    private String type;
    private String sex;
    private int age;
    private String major;
    private String grade;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Course>getCourse(){
        return LitePal.find(Student.class,id,true).getCourseList();

    }
    public List<Course> getDayCourseList(int day){
        List<Course> courseList=getCourse();
        List<Course> dayCourseList=new ArrayList<>();
        for (Course course:courseList){
            if (course.getDay()==day)
                dayCourseList.add(course);
        }
        return dayCourseList;
    }

    public List<Custom>getCustom(){
        return LitePal.where("student_id=?",String.valueOf(id)).find(Custom.class);
    }
    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Custom> getCustomList() {
        return customList;
    }

    public void setCustomList(List<Custom> customList) {
        this.customList = customList;
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
}
