package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    private int id;
    private String street_number;
    private String street_name;
    private String apartment_number;

    public Address() {
    }

    public Address(int id, String street_number, String street_name, String apartment_number) {
        this.id = id;
        this.street_number = street_number;
        this.street_name = street_name;
        this.apartment_number = apartment_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int address_id) {
        this.id = address_id;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        this.apartment_number = apartment_number;
    }
}