package com.example.demo.models;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Customer {
    @Id
    private int id;
    @NotNull
    @Size(min=2, max=45, message = "Navnet skal være mellem 2 og 45 bogstaver")
    @Pattern(regexp = "[a-zA-Z]*", message = "Må kun indeholde bogstaver")
    private String first_name;
    @NotNull
    @Size(min=2, max=45, message = "Navnet skal være mellem 2 og 45 bogstaver")
    @Pattern(regexp = "[a-zA-Z]*", message = "Må kun indeholde bogstaver")
    private String last_name;
    @NotNull
    @Size(min=1, max=15, message = "Indtast venligst et gyldigt telefonnummer")
    @Pattern(regexp = "[0-9]*", message = "Må kun indeholde bogstaver")
    private String phone_number;
    @NotNull
    private String birth_date;
    @NotNull
    @Size(min=1, max=15, message = "Indtast venligst et gyldigt kørekortnummer")
    private String drivers_license;

    //Joins the addresses and customer tables
    private int address_id;
    @NotNull
    @Size(min=1, max=10, message="Indtast venligst et gyldigt postnummer")
    @Pattern(regexp = "^[0-9a-zA-Z]*", message = "Indtast venligst et gyldigt postnummer")
    private String zip_code_id;


    public Customer() {
    }

    public Customer(int id, String first_name, String last_name, String phone_number, String birth_date, String drivers_license, int address_id, String zip_code_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.drivers_license = drivers_license;
        this.address_id = address_id;
        this.zip_code_id = zip_code_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDrivers_license() {
        return drivers_license;
    }

    public void setDrivers_license(String drivers_license) {
        this.drivers_license = drivers_license;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getZip_code_id() {
        return zip_code_id;
    }

    public void setZip_code_id(String zip_code) {
        this.zip_code_id = zip_code;
    }
}