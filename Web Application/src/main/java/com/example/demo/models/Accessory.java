package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Accessory {
    @Id
    private int id;
    private String accessory;

    public Accessory() {
    }

    public Accessory(int id, String accessory, int stock) {
        this.id = id;
        this.accessory = accessory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }
}

