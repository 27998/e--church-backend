package com.auca.echurch.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.echurch.model.ServiceRequest;
import com.auca.echurch.model.ServiceStatus;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,UUID>{
    List<ServiceRequest> findByStatusOrderByServiceDateAsc(ServiceStatus status);
    List<ServiceRequest> findByServiceDateBetweenOrderByServiceDateAsc(LocalDate start, LocalDate end);
}
