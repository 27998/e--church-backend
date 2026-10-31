package com.auca.echurch.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity 
@Table (name="member")
public class Member {

    @Id 
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String memberNumber;
    private String phone;
    private String cell;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMemberNumber() {
        return memberNumber;
    }
    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCell() {
        return cell;
    }
    public void setCell(String cell) {
        this.cell = cell;
    }



}
    