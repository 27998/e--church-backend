package com.auca.echurch.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.echurch.model.Pastor;
import com.auca.echurch.repository.PastorRepository;

@Service
public class PastorService {

    @Autowired 
    private PastorRepository pastorRepository;

    public String save(Pastor pastor){
        pastorRepository.save(pastor);
        return "Pastor saved successfully";
    }

    public List<Pastor>getAll(){
        return pastorRepository.findAll();
    }

    public Optional<Pastor>getById(UUID id){
        return pastorRepository.findById(id);

    }

    public String update(UUID id, Pastor pastor){
        if(!pastorRepository.existsById(id)){
            return "The pastor with that id does not exist";

        }
        pastor.setId(id);
        pastorRepository.save(pastor);
        return "Pastor updated successfully";

    }
    public String delete(UUID id){
        if (!pastorRepository.existsById(id)){
            return "The Pastor with that id does not exist";
        }
        pastorRepository.deleteById(id);
        return"pastor deleted successfully";
    }
}
 