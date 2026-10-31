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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auca.echurch.model.Member;
import com.auca.echurch.service.MemberService;

@RestController 
@RequestMapping("/api/members")
public class MemberController {

    @Autowired 
    private  MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity<String> save (@RequestBody Member member){
        String message = memberService.save(member);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Member>>getAll(){
        return new ResponseEntity<>(memberService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public  ResponseEntity<Object>getBy(@PathVariable UUID id){
        Optional<Member>member = memberService.getById(id);
        if(member.isPresent()){
            return new ResponseEntity<>( member.get(), HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>("The member with that id does not exist",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity<String>update(@PathVariable UUID id,
        @RequestBody Member member){
            String message = memberService.update(id,member);
            if(message.equals("The member with that id does not exist")){
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

            }
            else{
                return new ResponseEntity<>(message, HttpStatus.OK);

            }
        }
@DeleteMapping ("/delete/{id}")
public ResponseEntity<String>delete(@PathVariable UUID id){
    String message = memberService.delete(id);
    if(message.equals("The member with that id does not exist")){
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    else{
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

@GetMapping ("/by-last-name")
public ResponseEntity<List<Member>> getByLastName(@RequestParam String lastName){
   return new ResponseEntity<>(memberService.getByLastName(lastName), HttpStatus.OK);
}

@GetMapping ("/of-pastor/{pastorId}")
public ResponseEntity<Object> getOfPastor(@PathVariable UUID pastorId ){
    if(!memberService.pastorExists(pastorId)){
        return new ResponseEntity<>("The pastor with that id does not exist", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(memberService.getOfPastor(pastorId), HttpStatus.OK);
}
}
