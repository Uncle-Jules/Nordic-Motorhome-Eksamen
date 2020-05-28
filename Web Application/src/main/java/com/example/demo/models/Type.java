package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class    Type {
    @Id
    @Size(min=1, max=45, message="Indtast venligst en type på mellem 1 og 45 karakterer.")
    @Pattern(regexp = "[a-zA-ZæøåÆØÅ0-9:,.'\\s-]*", message="Type må kun indeholde tegnene (a-Å 0-9 : , . ' -).")
    private String type;
    @Min(value = 1, message = "Der skal mindst være én sengeplads i en autocamper")
    @Max(value = 10, message = "Der må maks være 10 sengepladser i en autocamper")
    private int beds;

    public Type() {
    }

    public Type(String type, int beds) {
        this.type = type;
        this.beds = beds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }
}
