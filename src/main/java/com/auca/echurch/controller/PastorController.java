package com.auca.echurch.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.echurch.model.Pastor;
import com.auca.echurch.service.PastorService;


@RestController 
@RequestMapping("/api/pastors")
public class PastorController {

    @Autowired 
    private PastorService pastorService;

    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody Pastor pastor){
        String message = pastorService.save(pastor);
        return new  ResponseEntity<>(message,HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List< Pastor>>getAll(){
        return new ResponseEntity<>(pastorService.getAll(),HttpStatus.OK);
            
        

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object>getById(@PathVariable UUID id){
        Optional<Pastor>pastor=pastorService.getById(id);
        if(pastor.isPresent()){
            return new ResponseEntity<>(pastor.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("The pastor with that id does not exist",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@PathVariable UUID id,
        @RequestBody Pastor pastor){
            String message = pastorService.update(id,pastor);
            if(message.equals("The pastor with that id does not exist")){
                return new  ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(message,HttpStatus.OK);
            }
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String>delete(@PathVariable UUID id){
            String message = pastorService.delete(id);
            if(message.equals("The pastor with that id does not exist")){
                return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(message,HttpStatus.OK);
            }
        }

    @GetMapping ("/by-ministry")
    public ResponseEntity<List<Pastor>> getByMinistry(@RequestParam String name){
        return new ResponseEntity<>(pastorService.getByMinistry(name), HttpStatus.OK);
    }
    @PutMapping ("/{pastorId}/ministries/{ministryId}")
    public ResponseEntity<String> addMinistry(@PathVariable UUID pastorId){
        String message = pastorService.addMinistry(pastorId, pastorId);
        if(message.equals("The pastor with that id does not exist")
                || message.equals("That ministry does not exist")){
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        else if (message.equals("The pastor already has that ministry")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @PatchMapping ("/{pastorId}/parish")
    public ResponseEntity<String> changeParish(@PathVariable UUID pastorId, @RequestParam int parishNumber){
        String message = pastorService.changeParish(pastorId, parishNumber);
        if (message.equals("The parish with that parishNumber does not exists")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("The parish is taken by another pastor")){
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

    }
}