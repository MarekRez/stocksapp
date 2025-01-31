package com.example.stocksapp.controller;

import com.example.stocksapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/clients/exists/{email}")
    public ResponseEntity<?> checkIfEmailExists(@PathVariable String email) {
        boolean exists = personService.doesEmailExist(email);
        return ResponseEntity.ok(Map.of("email", email, "exists", exists));
    }

}
