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
        if (serviceRequest.getServiceDate() != null
        && serviceRequest.getServiceDate().isBefore(LocalDate.now())){
            return  "Service request date cannot be in the past";

    }
        if (serviceRequest.getPastor() != null && serviceRequest.getServiceDate() != null
                && serviceRequestRepository.existsByPastorIdAndServiceDateAndStatusNot(
                    serviceRequest.getPastor().getId(),
                    serviceRequest.getServiceDate(),
                    ServiceStatus.DECLINED) ) {
                        return "Pastor already leads a service on that date";
         }
        serviceRequest.setStatus(ServiceStatus.REQUESTED);
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
        Optional<ServiceRequest> existing = serviceRequestRepository.findById(id);

        if(existing.isEmpty()){
            return "The service request with that id does not exist";
        }
        serviceRequest.setId(id);
        serviceRequest.setStatus(existing.get().getStatus());
        serviceRequestRepository.save(serviceRequest);
        return "Service request updated successfully";
    }

    public String updateStatus(UUID id, ServiceStatus newStatus){
        Optional<ServiceRequest> found = serviceRequestRepository.findById(id);
        if (found.isEmpty()){
            return "The service request with that id does not exist";

        }
        ServiceRequest serviceRequest = found.get();
        ServiceStatus current = serviceRequest.getStatus();

        boolean allowed = false;
        if (current == ServiceStatus.REQUESTED
            && (newStatus == ServiceStatus.APPROVED || newStatus == ServiceStatus.DECLINED )
        ){
            allowed = true;
        }
        else if (current == ServiceStatus.APPROVED
            && (newStatus == ServiceStatus.HELD || newStatus == ServiceStatus.DECLINED)
        ) {
            allowed = true;
        }
        if(!allowed){
            return "Cannot change status from " + current + " to " + newStatus;
        }

        serviceRequest.setStatus(newStatus);
        serviceRequestRepository.save(serviceRequest);
        return "Service request status updated successfully";
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
