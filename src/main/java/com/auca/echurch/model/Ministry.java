package com.auca.echurch.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "ministry")


public class Ministry {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private  UUID id;

    private String name;

    @ManyToMany (mappedBy = "ministries")
    @JsonIgnore 
    private List<Pastor> pastors;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public List<Pastor> getPastors() {
        return pastors;
    }
    public void setPastors(List<Pastor> pastors) {
        this.pastors = pastors;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    



}
