package com.auca.echurch.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.echurch.model.ServiceRequest;
import com.auca.echurch.model.ServiceStatus;
import com.auca.echurch.model.ServiceRecord;
import com.auca.echurch.repository.ServiceRecordRepository;
import com.auca.echurch.repository.ServiceRequestRepository;

@Service 
public class ServiceRecordService {
    @Autowired private ServiceRecordRepository serviceRecordRepository;
    @Autowired private ServiceRequestRepository serviceRequestRepository;

    public String save(UUID serviceRequestId, ServiceRecord serviceRecord){
       Optional<ServiceRequest> found = serviceRequestRepository.findById(serviceRequestId);
       if(found.isEmpty()){
        return "The service request with that id does not exist";
       }
       ServiceRequest serviceRequest = found.get();
       if (serviceRequest.getStatus() != ServiceStatus.HELD) {
        return "Service records are only for HELD service requests";

       }
       serviceRecord.setRecordedDate(LocalDate.now());
       serviceRecord.setServiceRequest(serviceRequest);
       serviceRecordRepository.save(serviceRecord);
       return "Service record saved successfully";
    }

    public List<ServiceRecord> getAll(){
        return serviceRecordRepository.findAll();
    }
    public Optional<ServiceRecord> getById(UUID id){
        return serviceRecordRepository.findById(id);
    }
    public List<ServiceRecord> getByMember(String memberNumber){
        return serviceRecordRepository.findByMemberNumber(memberNumber);
    }
    public String delete(UUID id){
        if(!serviceRecordRepository.existsById(id)){
            return "The service record with that id does not exist";
        }
        serviceRecordRepository.deleteById(id);
        return "Service record deleted successfully";
    }
}
