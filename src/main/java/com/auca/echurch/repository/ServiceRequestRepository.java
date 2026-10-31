package com.auca.echurch.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.echurch.model.ServiceRequest;
import com.auca.echurch.model.ServiceStatus;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,UUID>{
    List<ServiceRequest> findByStatusOrderByServiceDateAsc(ServiceStatus status);
    List<ServiceRequest> findByServiceDateBetweenOrderByServiceDateAsc(LocalDate start, LocalDate end);
    boolean existsByPastorIdAndServiceDateAndStatusNot(UUID pastorId, LocalDate serviceDate, ServiceStatus status);

    @Query 
    ("SELECT r.status, COUNT(r) FROM ServiceRequest r GROUP BY r.status")
    List<Object[]> countRequestByStatus();

    @Modifying 
    @Query 
    ("UPDATE ServiceRequest r SET r.status = :declined WHERE r.pastor.id = :pastorId And r.serviceDate = :date AND r.status <> :held")
    int cancelDay(@Param ("pastorId") UUID pastorId,
                    @Param ("date") LocalDate date,
                    @Param ("declined") ServiceStatus declined,
                    @Param ("held") ServiceStatus held);

}
