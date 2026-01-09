package com.franco.apirffranco.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.franco.apirffranco.model.Person;

@Repository
public class PersonRepository {
    private ArrayList<Person> listPerson = new ArrayList<>();

    public void save(Person person){
        this.listPerson.add(person);
    }

    public List<Person> list(){
        return this.listPerson;
    }
}
