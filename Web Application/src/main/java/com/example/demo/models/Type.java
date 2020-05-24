package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class    Type {
    @Id
    private String type;
    private int beds;

    public Type() {
    }

    public Type(String type, int beds) {
        this.type = type;
        this.beds = beds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }
}
