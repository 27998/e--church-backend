package com.auca.echurch.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.echurch.model.Member;

public interface MemberRepository extends JpaRepository<Member, UUID>  {

}
