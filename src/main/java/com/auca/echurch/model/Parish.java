package com.auca.echurch.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="parish")

public class Parish {

    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private int parishNumber;
    private  String name;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public int getParishNumber() {
        return parishNumber;
    }
    public void setParishNumber(int parishNumber) {
        this.parishNumber = parishNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
