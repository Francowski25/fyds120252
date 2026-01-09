package com.franco.apirffranco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.apirffranco.model.Person;
import com.franco.apirffranco.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person){
        this.personRepository.save(person);
        return person;
    }

    public List<Person> list(){
        return this.personRepository.list();
    }
}
