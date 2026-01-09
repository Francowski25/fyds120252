package com.franco.apirffranco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franco.apirffranco.model.Person;
import com.franco.apirffranco.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/insert")
    public ResponseEntity<Person> add(@RequestBody Person person) {
        Person p = personService.save(person);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/getall")
	public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(personService.list());
    }
    
}
