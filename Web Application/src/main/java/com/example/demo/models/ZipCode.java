package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ZipCode {
    @Id
    private String zip_code;
    private String city;
    private String country;

    public ZipCode() {

    }

    public ZipCode(String zip_code, String city, String country) {
        this.zip_code = zip_code;
        this.city = city;
        this.country = country;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
