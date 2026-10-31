package com.auca.echurch.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.echurch.model.Pastor;

public interface PastorRepository extends JpaRepository<Pastor, UUID> {
    @Query("SELECT p FROM Pastor p JOIN p.ministries m WHERE LOWER(m.name) = LOWER(:name)")
    List<Pastor> findByMinistriesName(@Param ("name") String name);
}
