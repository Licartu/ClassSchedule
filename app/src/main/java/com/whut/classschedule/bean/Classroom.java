package com.whut.classschedule.bean;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019/5/6.
 */

public class Classroom  extends LitePalSupport implements Serializable{
    private int Id;
    private String name;
    private List<Course> courseList=new ArrayList<>();

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Classroom(int id, String name, List<Course> courseList) {

        Id = id;
        this.name = name;
        this.courseList = courseList;
    }
}
