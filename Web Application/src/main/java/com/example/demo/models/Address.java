package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Address {
    @Id
    private int id;
    @NotNull
    @Size(min=1, max=5, message = "Gadenummer skal være mellem 1 og 5 karaktere lang")
    private String street_number;
    @NotNull
    @Size(min=1, max=45, message = "Indtast venligst et gyldigt gadenavn på max 45 karakterer")
    @Pattern(regexp = "[a-zA-Z\\s'-]*", message = "Må kun indeholde bogstaver")
    private String street_name;
    private String apartment_number;
    private String zip_code;

    public Address() {
    }

    public Address(int id, String street_number, String street_name, String apartment_number, String zip_code) {
        this.id = id;
        this.street_number = street_number;
        this.street_name = street_name;
        this.apartment_number = apartment_number;
        this.zip_code = zip_code;
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

        return apartment_number == null ? "" : apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        this.apartment_number = apartment_number;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}
