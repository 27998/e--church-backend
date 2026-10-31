package com.auca.echurch.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "service_request")
public class ServiceRequest {

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Pastor getPastor() {
        return pastor;
    }

    public void setPastor(Pastor pastor) {
        this.pastor = pastor;
    }
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate serviceDate;

    @Enumerated (EnumType.STRING)
    private ServiceStatus status;

    private String serviceType;

    @ManyToOne 
    @JoinColumn (name = "member_id")
    private Member member;

    @ManyToOne 
    @JoinColumn (name = "pastor_id")
    private Pastor pastor;

    public ServiceRequest(){}
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public LocalDate getServiceDate() {
        return serviceDate;
    }
    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }
    public ServiceStatus getStatus() {
        return status;
    }
    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}
