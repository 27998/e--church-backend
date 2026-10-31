package com.auca.echurch.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.echurch.model.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,UUID>{

}
