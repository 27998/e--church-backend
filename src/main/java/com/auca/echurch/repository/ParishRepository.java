package com.auca.echurch.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auca.echurch.model.Parish;

public interface ParishRepository extends JpaRepository<Parish, UUID> {
    @Query ("SELECT l FROM Parish l WHERE NOT EXISTS (SELECT p FROM Pastor p WHERE p.parish = l) ORDER BY l.parishNumber")
    List<Parish> findFreeParishes();
}
