package com.example.server.entity;

public class TypeAndNum {
    private String type;
    private Integer num;

    public TypeAndNum() {
    }

    public TypeAndNum(String type, Integer num) {
        this.type = type;
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
