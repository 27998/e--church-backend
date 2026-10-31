package com.auca.echurch.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.echurch.model.ServiceRequest;
import com.auca.echurch.model.ServiceStatus;
import com.auca.echurch.service.ServiceRequestService;

@RestController 
@RequestMapping("/api/service-requests")
public class ServiceRequestController {
    @Autowired 
    private ServiceRequestService serviceRequestService;
     
    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody ServiceRequest serviceRequest){
        String message = serviceRequestService.save(serviceRequest);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceRequest>>getAll(){
        return new ResponseEntity<>(serviceRequestService.getAll(),
    HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById (@PathVariable UUID id){
        Optional<ServiceRequest>serviceRequest = 
        serviceRequestService.getById(id);
        if(serviceRequest.isPresent()){
            return new ResponseEntity<>(serviceRequest.get(),
        HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("The service requst with that id does not exist",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@PathVariable UUID id,
        @RequestBody ServiceRequest serviceRequest ){
            String message = serviceRequestService.update(id, serviceRequest);
            if(message.equals("The service request with that id does not exist")){
                return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

            }
            else{
                return new ResponseEntity<>(message,HttpStatus.OK);
            }
        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable UUID id){
        String message = serviceRequestService.delete(id);
        if(message.equals("The service request with that id does not exist")){
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
        else{
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
    }
    @GetMapping ("/by-status")
    public ResponseEntity<List<ServiceRequest>> getByStatus(@RequestParam ServiceStatus status){
        return new ResponseEntity<>(serviceRequestService.getByStatus(status), HttpStatus.OK);
    }

    @GetMapping ("/between")
    public ResponseEntity<List<ServiceRequest>> getBetween(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return new ResponseEntity<>(serviceRequestService.getBetween(startDate, endDate), HttpStatus.OK);
    }

}
