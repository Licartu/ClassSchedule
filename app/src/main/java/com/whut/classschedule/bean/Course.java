package com.whut.classschedule.bean;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019/5/6.
 */

public class Course extends LitePalSupport implements Serializable{
    /*day:周几上课
* startWeek:起始周
* endWeek:结束周
* classTime上课时间
* hour：课时
* tool：带的文具
* singleOrDouble:单双周，0为不区分，1为单周，2为双周
* */
    private int id;
    private String courseName;
    private Teacher teacher;
    private Classroom classroom;
    private int day;
    private int startWeek;
    private int endWeek;
    private int classTime;
    private int hour;
    private String tool;
    private int singleOrDouble;
    private List<Student> studentList=new ArrayList<>();
    private List<Job> jobList=new ArrayList<>();

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getClassTime() {
        return classTime;
    }

    public void setClassTime(int classTime) {
        this.classTime = classTime;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public int getSingleOrDouble() {
        return singleOrDouble;
    }

    public void setSingleOrDouble(int singleOrDouble) {
        this.singleOrDouble = singleOrDouble;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
