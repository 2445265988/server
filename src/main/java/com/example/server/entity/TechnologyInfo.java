package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_technologyInfo")
public class TechnologyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //编号
    private String sname;   //姓名
    private String sno;    //学号
    private String title; //题目
    private String keyWords; //关键词
    private String technology; //所用的技术
    private String semester; //学期

    public TechnologyInfo() {
    }

    public TechnologyInfo(String sno, String sname,String title,  String keyWords, String technology,String semester) {
        this.sname = sname;
        this.sno = sno;
        this.title = title;
        this.keyWords = keyWords;
        this.technology = technology;
        this.semester = semester;
    }

    public TechnologyInfo(String sno, String sname, String title, String technology, String semester) {
        this.sname = sname;
        this.sno = sno;
        this.title = title;
        this.technology = technology;
        this.semester = semester;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
