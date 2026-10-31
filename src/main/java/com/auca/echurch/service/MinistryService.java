package com.auca.echurch.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.echurch.model.Ministry;
import com.auca.echurch.repository.MinistryRepository;

@Service
public class MinistryService {
    @Autowired 
    private MinistryRepository ministryRepository;

    public String save(Ministry ministry){
        ministryRepository.save(ministry);
        return "Ministry saved successfully";

    }
public List<Ministry> getAll() {
    return ministryRepository.findAll();
}
public Optional<Ministry> getById(UUID id){
    return ministryRepository.findById(id);

}
public String update(UUID id,Ministry ministry){
    if(!ministryRepository.existsById(id)){
        return "The ministry with that id does not exist";
    }
    ministry.setId(id);
    ministryRepository.save(ministry);
    return "Ministry updated successfully";
}
public String delete (UUID id){
    if(!ministryRepository.existsById(id)){
        return "The ministry with that id does not exist";
        
    }
    
ministryRepository.deleteById(id);
return"Ministry deleted successfully";
    }

public List<Ministry> getUnused(){
    return ministryRepository.findUnusedMinistries();
}
}


