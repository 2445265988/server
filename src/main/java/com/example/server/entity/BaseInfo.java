package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_baseInfo")
public class BaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 编号
    private String sno; // 学号
    private String sname; // 姓名
    private String title; // 论文题目
    private String type; // 论文类型
    private String origin; // 课题来源
    private String teacher; // 指导教师
    private String semember; // 所在学期

    public BaseInfo() {
    }

    public BaseInfo(Integer id, String sno, String sname, String title, String type, String origin, String teacher,String semember) {
        this.id = id;
        this.sno = sno;
        this.sname = sname;
        this.title = title;
        this.type = type;
        this.origin = origin;
        this.teacher = teacher;
        this.semember = semember;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSemember() {
        return semember;
    }

    public void setSemember(String semember) {
        this.semember = semember;
    }


}
