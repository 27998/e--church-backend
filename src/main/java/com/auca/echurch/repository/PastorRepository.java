package com.auca.echurch.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.echurch.model.Pastor;

public interface PastorRepository extends JpaRepository<Pastor, UUID> {

}
