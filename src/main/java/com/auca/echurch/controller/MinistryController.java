package com.auca.echurch.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.auca.echurch.model.Ministry;
import com.auca.echurch.service.MinistryService;

@RestController 
@RequestMapping("/api/ministries")

public class MinistryController {

    @Autowired 
    private MinistryService ministryService;

    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody Ministry ministry){

        String message = ministryService.save(ministry);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Ministry>>getAll(){
        return new ResponseEntity<>(ministryService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object>getByid(@PathVariable UUID id){
        Optional<Ministry>ministry = ministryService.getById(id);
        if(ministry.isPresent()){
            return new ResponseEntity<>(ministry.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("The ministry with that id does not exist", HttpStatus.NOT_FOUND);
        }

    }
   
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id,
        @RequestBody Ministry ministry){
            String message = ministryService.update(id,ministry);
            if (message.equals("The ministry with that id does not exist")){
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(message,HttpStatus.OK);
            }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable UUID id){
        String message = ministryService.delete(id);
        if (message.equals("The ministry with that id does not exist")){
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
        else{
            return new ResponseEntity<>(message,HttpStatus.OK);

        }
    }
            }
        

        
    


