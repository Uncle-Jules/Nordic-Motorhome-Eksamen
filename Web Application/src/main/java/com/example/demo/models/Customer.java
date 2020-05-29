package com.example.demo.models;

import javax.validation.constraints.*;

public class Customer extends Person {
    @Size(min=1, max=15, message = "Indtast venligst et gyldigt kørekortnummer på max 45 karakterer.")
    private String drivers_license;

    public Customer() {
    }

    public Customer(int id, String first_name, String last_name, String phone_number, String birth_date, String drivers_license, int address_id) {
        super(id, first_name, last_name, phone_number, birth_date, address_id);
        this.drivers_license = drivers_license;
    }

    public String getDrivers_license() {
        return drivers_license;
    }

    public void setDrivers_license(String drivers_license) {
        this.drivers_license = drivers_license;
    }



}