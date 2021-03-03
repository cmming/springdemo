package com.example.springdemo.dao;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 21:33 2021/2/22
 */
@NodeEntity
@Data
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long born;

    @Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
    private Set<Movie> actedIn = new HashSet<>();

}
