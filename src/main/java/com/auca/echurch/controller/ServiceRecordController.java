package com.auca.echurch.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.echurch.model.ServiceRecord;
import com.auca.echurch.service.ServiceRecordService;

@RestController 
@RequestMapping ("/api/service-records")
public class ServiceRecordController {
    @Autowired private ServiceRecordService serviceRecordService;

    @PostMapping ("/save")
    public ResponseEntity<String> save(@RequestParam UUID serviceRequestId, @RequestBody ServiceRecord serviceRecord){
        String message = serviceRecordService.save(serviceRequestId, serviceRecord);
        if (message.equals("The service request with that id does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals("Service records are only for HELD service requests")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    @GetMapping ("/member/{memberNumber}")
    public ResponseEntity<List<ServiceRecord>> getByMember(@PathVariable String memberNumber){
        return new ResponseEntity<>(serviceRecordService.getByMember(memberNumber), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = serviceRecordService.delete(id);
        if (message.equals("The service record with that id does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}
