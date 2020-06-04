package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
 * Coded by Michael
 */

@Entity
public class Person {
    @Id
    private int id;
    @Size(min=2, max=45, message = "Navnet skal være mellem 2 og 45 bogstaver.")
    @Pattern(regexp = "[a-zA-ZæøåÆØÅ,.'-]*", message = "Fornavn må kun indeholde tegnene (a-Å 0-9 , . ' -).")
    private String first_name;
    @Size(min=2, max=45, message = "Navnet skal være mellem 2 og 45 bogstaver.")
    @Pattern(regexp = "[a-zA-ZæøåÆØÅ,.'-]*", message = "Efternavn må kun indeholde bogstaver.")
    private String last_name;
    @Size(min=1, max=15, message = "Indtast venligst et gyldigt telefonnummer.")
    @Pattern(regexp = "[0-9+-]*", message = "Telefonnummer må kun indeholde bogstaver.")
    private String phone_number;
    @NotEmpty(message="Indtast venligst en fødselsdato.")
    private String birth_date;
    private int address_id;

    public Person() {
    }

    public Person(int id, String first_name, String last_name, String phone_number, String birth_date, int address_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.address_id = address_id;
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

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
