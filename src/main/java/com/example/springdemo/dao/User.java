package com.example.springdemo.dao;

import com.example.springdemo.enums.UserStatusEnums;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 用户状态 默认正常 .
     */
    private Integer status = UserStatusEnums.NORMAL.getStatusCode();
}
