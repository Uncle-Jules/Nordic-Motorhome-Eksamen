package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Accessory {
    @Id
    private int id;
    @NotNull
    @Size(min=1, max=45, message = "Indtast venligst tilbehør på max 45 karakterer")
    private String accessory;

    public Accessory() {
    }

    public Accessory(int id, String accessory) {
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

