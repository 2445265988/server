package com.example.server.entity.neo4j;

public class EchartsData {
    private String name;
    private Object des;
    private Integer symbolSize;
    private Integer category;

    public EchartsData() {
    }

    public EchartsData(String name, Object des, Integer symbolSize, Integer category) {
        this.name = name;
        this.des = des;
        this.symbolSize = symbolSize;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDes() {
        return des;
    }

    public void setDes(Object des) {
        this.des = des;
    }

    public Integer getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Integer symbolSize) {
        this.symbolSize = symbolSize;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
