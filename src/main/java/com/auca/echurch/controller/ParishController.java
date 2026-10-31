package com.auca.echurch.controller;

import java.util.HashSet;
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

import com.auca.echurch.model.Parish;
import com.auca.echurch.service.ParishService;

@RestController 
@RequestMapping("/api/parishes")
public class ParishController {

    @Autowired 
    private ParishService parishService;

    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody Parish parish){
        String message = parishService.save(parish);
        return new  ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Parish>>getAll(){
        return new ResponseEntity<>(parishService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBy(@PathVariable UUID id){
        Optional<Parish>parish = parishService.getById(id);
        if (parish.isPresent()){
            return new ResponseEntity<>(parish.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("The parish with that id does not exist",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@PathVariable UUID id,
        @RequestBody Parish parish){
            String message = parishService.update(id,parish);
            if(message.equals("The parish with that id does not exist")){
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        }
    
@DeleteMapping("/delete/{id}")
public ResponseEntity<String>delete(@PathVariable UUID id){
    String message = parishService.delete(id);
    if(message.equals("The parish with that id does not exist")){

    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
}
else{
    return  new ResponseEntity<>(message,HttpStatus.OK);

}

}
}

