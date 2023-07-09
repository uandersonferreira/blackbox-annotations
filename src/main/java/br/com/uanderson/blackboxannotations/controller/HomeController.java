package br.com.uanderson.blackboxannotations.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/home"})
public class HomeController {
    @GetMapping
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Hello, world", HttpStatus.OK);
    }




}//class
