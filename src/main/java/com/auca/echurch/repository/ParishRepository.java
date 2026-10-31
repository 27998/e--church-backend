package com.auca.echurch.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.echurch.model.Parish;

public interface ParishRepository extends JpaRepository<Parish, UUID> {

}
