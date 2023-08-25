package com.example.server.entity.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Topic {
    @Id
    @GeneratedValue
    private Long id;

    private String name; //技术类别

    @Relationship(type = "have", direction = Relationship.OUTGOING)
    private List<Thesis> theses;

    public List<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(List<Thesis> theses) {
        this.theses = theses;
    }

    public Topic() {
    }

    public Topic(String name) {
        this.name = name;
    }

    public Topic(String type, List<Thesis> thesesList) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
