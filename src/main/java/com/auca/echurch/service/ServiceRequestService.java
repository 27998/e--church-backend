package com.auca.echurch.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.echurch.model.ServiceRequest;
import com.auca.echurch.model.ServiceStatus;
import com.auca.echurch.repository.ServiceRequestRepository;

@Service
public class ServiceRequestService {

    @Autowired 
    private ServiceRequestRepository serviceRequestRepository;

    public String save(ServiceRequest serviceRequest){
        serviceRequestRepository.save(serviceRequest);
        return "Service request saved successfully";
    }
    public List<ServiceRequest>getAll(){
        return serviceRequestRepository.findAll();
    }
    public Optional<ServiceRequest>getById(UUID id){
        return serviceRequestRepository.findById(id);

    }
    public String update(UUID id, ServiceRequest serviceRequest){
        if(!serviceRequestRepository.existsById(id)){
            return "The service request with that id does not exist";
        }
        serviceRequest.setId(id);
        serviceRequestRepository.save(serviceRequest);
        return "Service request updated successfully";
    }
    public List<ServiceRequest> getByStatus(ServiceStatus status){
        return serviceRequestRepository.findByStatusOrderByServiceDateAsc(status);
    }
    public List<ServiceRequest> getBetween(LocalDate startDate, LocalDate endDate){
        return serviceRequestRepository.findByServiceDateBetweenOrderByServiceDateAsc(startDate, endDate);
    }
    public String delete(UUID id){
        if(!serviceRequestRepository.existsById(id)){
            return " The service request with that id does not exist";
        }
        serviceRequestRepository.deleteById(id);
        return "Service request deleted successfully";
    }
}
