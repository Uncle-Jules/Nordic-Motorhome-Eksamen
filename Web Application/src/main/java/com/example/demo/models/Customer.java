package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
   @Id
    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String birth_date;
    private String payment_details;
    private String drivers_license;

    //Joins the addresses and customer tables
    private int address_id;
    private String zip_code_id;


    public Customer() {
    }

    public Customer(int id, String first_name, String last_name, String phone_number, String birth_date, String payment_details, String drivers_license, int address_id, String zip_code_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.payment_details = payment_details;
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

    public String getPayment_details() {
        return payment_details;
    }

    public void setPayment_details(String payment_details) {
        this.payment_details = payment_details;
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