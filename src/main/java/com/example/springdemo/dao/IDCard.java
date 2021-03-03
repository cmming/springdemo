/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户身份证 .
 * 1、和用户表关联，
 * 2、一个用户拥有一个身份证，
 * 3、关系维护在身份证表中
 * 4、用户可以没有身份证，但是身份证不能没有用户 optional(true表示不能为null)
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class IDCard {

    /**
     * 身份证编号 .
     */
    @Id
    private String cardNum;


    /**
     * mappedBy的值是指另一方的实体里面属性的字段，而不是数据库字段，也不是实体对象的名字。
     * 即另一方配置了@JoinColumn或者@JoinTable注解的属性的字段名称
     * 比如此时 User实体中@JoinColumn的属性字段为idCard，所以这里值就是idCard
     */
    @OneToOne(mappedBy = "idCard", optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;
}
