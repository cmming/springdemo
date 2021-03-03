package com.example.springdemo.repository;

import com.example.springdemo.dao.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    /**
     * 根据id查询 .
     * @param id
     * @return
     */
    Optional<Person> findById(Long id);

    /**
     * 根据名称查找 .
     * @param name 名称
     * @return
     */
    Person findByName(String name);
}
