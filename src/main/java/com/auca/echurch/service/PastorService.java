package com.auca.echurch.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auca.echurch.model.Ministry;
import com.auca.echurch.model.Parish;
import com.auca.echurch.model.Pastor;
import com.auca.echurch.repository.MinistryRepository;
import com.auca.echurch.repository.ParishRepository;
import com.auca.echurch.repository.PastorRepository;

@Service
public class PastorService {

    @Autowired 
    private PastorRepository pastorRepository;
    @Autowired private MinistryRepository ministryRepository;
    @Autowired private ParishRepository parishRepository;

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

    public List<Pastor> getByMinistry(String name){
        return pastorRepository.findByMinistriesName(name);

    }

    @Transactional 
    public String addMinistry(UUID pastorId, UUID ministryId){
        Optional<Pastor> pastor = pastorRepository.findById(pastorId);
        if (pastor.isEmpty()) {
            return "The pastor with that id does not exist";
        }
        Optional<Ministry> ministry = ministryRepository.findById(ministryId);
        if(ministry.isEmpty()){
            return "That ministry does not exist";
        }
        Pastor p = pastor.get();
        for (Ministry m: p.getMinistries()){
            if(m.getId().equals(ministryId)){
                return "The pastor already has that ministry";
            }
        }
        p.getMinistries().add(ministry.get());
        pastorRepository.save(p);
        return "Ministry added to the pastor";
    }
    public String changeParish(UUID pastorId, int parishNumber) {
        Optional<Pastor> pastor = pastorRepository.findById(pastorId);
        if (pastor.isEmpty()) {
            return "The pastor with that id does not exist";
        }
        Optional<Parish> parish = parishRepository.findByParishNumber(parishNumber);
        if (parish.isEmpty()) {
            return "The parish with that parishNumber does not exist";
        }
        Pastor p = pastor.get();
        Parish newParish = parish.get();
        boolean sameParish = p.getParish() != null && p.getParish().getId().equals(newParish.getId());
        if (!sameParish && pastorRepository.existsByParish(newParish)) {
            return "That parish is taken by another pastor";
        }
        p.setParish(newParish);
        pastorRepository.save(p);
        return "Parish changed successfully";
    }

}
 