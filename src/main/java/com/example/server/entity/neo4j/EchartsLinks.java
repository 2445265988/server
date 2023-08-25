package com.example.server.entity.neo4j;

public class EchartsLinks {
    private String source;
    private String target;
    private String name;
    private String des;

    public EchartsLinks() {
    }

    public EchartsLinks(String source, String target, String name, String des) {
        this.source = source;
        this.target = target;
        this.name = name;
        this.des = des;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
