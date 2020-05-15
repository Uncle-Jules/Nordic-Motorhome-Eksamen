package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Motorhome {
    @Id
    private int id;
    private String type;
    private String brand;
    private String model;
    private int mileage;
    private double price_per_day;
    private String registration_number;

    public Motorhome(int id, String type, String brand, String model, int mileage, double price_per_day, String registration_number) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.price_per_day = price_per_day;
        this.registration_number = registration_number;
    }

    public Motorhome() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }
}
