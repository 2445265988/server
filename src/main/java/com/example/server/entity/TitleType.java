package com.example.server.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_titleType")
public class TitleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //编号

    private String type; //词

    private Date addDate; //添加时间

    public TitleType() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
