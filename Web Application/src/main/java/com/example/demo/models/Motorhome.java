package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Motorhome {
    @Id
    private int id;
    private String type;
    @Size(min=1, max=45, message="Indtast venligst en type på mellem 1 og 45 karakterer")
    @Pattern(regexp = "[a-zA-ZæøåÆØÅ0-9:,.'-]*", message="Brand må kun indeholde tegnene (a-Å 0-9 : , . ' -).")
    private String brand_name;
    @Size(min=1, max=45, message="Indtast venligst en type på mellem 1 og 45 karakterer.")
    @Pattern(regexp = "[a-zA-ZæøåÆØÅ0-9:,.'-]*", message="Model må kun indeholde tegnene (a-Å 0-9 : , . ' -).")
    private String model;
    private int mileage;
    private double price_per_day;
    @Size(min=2, max=9, message="Nummerpladen kan skal have mellem 2 og 9 karakterer (mellemrum inkluderet)")
    @Pattern(regexp = "[a-zA-ZæøåÆØÅ0-9]*", message="Nummerpladen må kun indeholde tegnene (a-Å 0-9)")
    private String registration_number;

    public Motorhome(int id, String type, String brand_name, String model, int mileage, double price_per_day, String registration_number) {
        this.id = id;
        this.type = type;
        this.brand_name = brand_name;
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

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand) {
        this.brand_name = brand;
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
