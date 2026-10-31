package com.auca.echurch.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.echurch.model.Member;

public interface MemberRepository extends JpaRepository<Member, UUID>  {
    List<Member> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);   

    @Query ("SELECT DISTINCT r.member FROM ServiceRequest r WHERE r.pastor.id = :pastorId")
    List<Member> findMembersOfPastor(@Param ("pastorId") UUID pastorid);
}
