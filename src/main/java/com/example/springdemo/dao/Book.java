/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * 书籍实体 .
 * 一个用户可能拥有多本书
 * 关系维护在用户表中
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    private String name;
}
