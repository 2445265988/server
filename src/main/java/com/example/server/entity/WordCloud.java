package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_wordCloud")
public class WordCloud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //编号

    private String name;
    private Integer value;

    public WordCloud() {
    }
    public WordCloud(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
