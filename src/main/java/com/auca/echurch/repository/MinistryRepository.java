package com.auca.echurch.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auca.echurch.model.Ministry;

public interface MinistryRepository extends JpaRepository<Ministry,UUID> {
        @Query ("SELECT m FROM Ministry m WHERE m.pastors IS EMPTY")
    List<Ministry> findUnusedMinistries();
}
