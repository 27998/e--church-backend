package com.auca.echurch.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.echurch.model.ServiceRecord;
import com.auca.echurch.model.ServiceRequest;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, UUID>{
    @Query ("SELECT d FROM ServiceRecord d WHERE d.serviceRequest.member.memberNumber = :memberNumber ORDER BY d.recordedDate DESC")
    List<ServiceRecord> findByMemberNumber(@Param("memberNumber") String memberNumber);

    boolean existsByServiceRequest(ServiceRequest serviceRequest);
}
