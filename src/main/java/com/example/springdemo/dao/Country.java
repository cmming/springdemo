/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
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
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Country {


    private String name;

    @Id
    private Integer code;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "countries")
    @JsonIgnore
    private List<User> users;

    /**
     * 创建时间
     */
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Column(name = "last_update_time", nullable = false)
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;
}
