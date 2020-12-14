/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 国家 .
 * 一个国家有多个人
 * 假设一个人也可以拥有多个国籍
 *
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class Country {


    private String name;

    @Id
    private Integer code;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "countries")
    @JsonIgnore
    private List<User> users;
}
