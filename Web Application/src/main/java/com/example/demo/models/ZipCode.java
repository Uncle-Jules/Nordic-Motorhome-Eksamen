package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class ZipCode {
    @Id
    @NotNull
    @Size(min=2, max =10, message = "Indtast venligst et gyldigt postnummer.")
    @Pattern( regexp = "[a-zA-Z0-9-]*")
    private String zip;
    @NotNull
    @Size(min=1, max=45, message = "By skal indeholde mellem 1-45 karakterer.")
    private String city;
    @NotNull
    @Size(min=1, max=45, message = "Land skal indeholde mellem 1-45 karakterer.")
    private String country;

    public ZipCode() {

    }

    public ZipCode(String zip, String city, String country) {
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip_code) {
        this.zip = zip_code;
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
