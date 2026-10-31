package com.auca.echurch.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.echurch.model.Parish;
import com.auca.echurch.repository.ParishRepository;


@Service
public class ParishService {

    @Autowired 
    private ParishRepository parishRepository;
     public String save(Parish parish){
        parishRepository.save(parish);
        return"Parish saved successfully";
     }
     public List <Parish>getAll(){
        return parishRepository.findAll();
     }

     public Optional<Parish>getById(UUID id){
        return parishRepository.findById(id);
     }

     public String update(UUID id, Parish parish){
        if(!parishRepository.existsById(id)){
            return "The parish with that id does not exist";
        }

        parish.setId(id);
        parishRepository.save(parish);
        return "parish updated successfully";
    }

    public String delete(UUID id){
        if(!parishRepository.existsById(id)){
            return "The parish with that id does not exist";

        }
        parishRepository.deleteById(id);
        return "Parish deleted successfully";
    }

    public List<Parish> getFree(){
        return parishRepository.findFreeParishes();
    }
}
