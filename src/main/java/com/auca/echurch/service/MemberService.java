package com.auca.echurch.service;

import com.auca.echurch.model.Member;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.auca.echurch.repository.MemberRepository;

@Service
public class MemberService {
  
    @Autowired 
    private MemberRepository memberRepository;
    public String save(Member member){
        memberRepository.save(member);
        return "Member saved successfully";
    }

public List<Member>getAll(){
    return memberRepository.findAll();
}

public Optional<Member>getById(UUID id){
    return memberRepository.findById(id);
}

public String update(UUID id, Member member){
    if(!memberRepository.existsById(id)){
        return "The  member with that id does not exist";
    }
    member.setId(id);
    memberRepository.save(member);
    return "Member updated successfully";
}
public String delete(UUID id){
    if(!memberRepository.existsById(id)){
        return "The member with that id does not exist";

    }
    memberRepository.deleteById(id);
    return "Member deleted successfully";
}
}
