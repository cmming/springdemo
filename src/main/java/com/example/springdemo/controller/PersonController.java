package com.example.springdemo.controller;

import com.example.springdemo.dao.Person;
import com.example.springdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 21:45 2021/2/22
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/findByName")
    public Person findByName(@RequestParam final String name){
        return personRepository.findByName(name);
    }

    @PostMapping
    public Person store(@RequestBody final Person person){
        return personRepository.save(person);
    }
}
