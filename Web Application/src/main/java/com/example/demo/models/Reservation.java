package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Reservation {
    @Id
    private int id;
    private String start_date;
    private String end_date;
    private int distance_to_pickup;
    private int accessory_id;


    //Field for customer to be shown in reservation.
    private String first_name;
    private String last_name;

    //Fields for motorhomes to be shown in reservation.
    private String model;
    private String brand_name;
    private String type;


    //Foreignkeys for customer and motorhome.
    private int motorhome_id;
    private int customer_id;

    public Reservation() {
    }

    public Reservation(int id, String start_date, String end_date, int distance_to_pickup, int accessory_id, String first_name, String last_name, String model, String brand_name, String type, int motorhome_id, int customer_id) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.distance_to_pickup = distance_to_pickup;
        this.accessory_id = accessory_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.model = model;
        this.brand_name = brand_name;
        this.type = type;
        this.motorhome_id = motorhome_id;
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMotorhome_id() {
        return motorhome_id;
    }

    public void setMotorhome_id(int motorhome_id) {
        this.motorhome_id = motorhome_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        start_date = start_date.replace("T", " ") + ":00";
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        end_date = end_date.replace("T", " ") + ":00";
        this.end_date = end_date;
    }

    public int getDistance_to_pickup() {
        return distance_to_pickup;
    }

    public void setDistance_to_pickup(int distance_to_pickup) {
        this.distance_to_pickup = distance_to_pickup;
    }

    public int getAccessory_id() {
        return accessory_id;
    }

    public void setAccessory_id(int accessory_id) {
        this.accessory_id = accessory_id;
    }
}