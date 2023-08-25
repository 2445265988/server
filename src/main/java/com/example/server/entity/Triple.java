package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "r_triple")
public class Triple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String entity_1;
    private String entity_2;
    private String relation;

    public Triple() {
    }

    public Triple(int id, String entity_1, String entity_2, String relation) {
        this.id = id;
        this.entity_1 = entity_1;
        this.entity_2 = entity_2;
        this.relation = relation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntity_1() {
        return entity_1;
    }

    public void setEntity_1(String entity_1) {
        this.entity_1 = entity_1;
    }

    public String getEntity_2() {
        return entity_2;
    }

    public void setEntity_2(String entity_2) {
        this.entity_2 = entity_2;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "id=" + id +
                ", entity_1='" + entity_1 + '\'' +
                ", entity_2='" + entity_2 + '\'' +
                ", relation='" + relation + '\'' +
                '}';
    }
}
