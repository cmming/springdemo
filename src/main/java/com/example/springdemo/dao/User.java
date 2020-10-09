package com.example.springdemo.dao;

import com.example.springdemo.enums.UserStatusEnums;
import com.example.springdemo.utils.serializer.UserStatusCode2StatusNameSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 在输出的Json数据中隐藏密码，只能输入不输出 .
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 用户状态 默认正常 .
     */
    /**
     * 响应数据格式转换
     */
    @JsonSerialize(using = UserStatusCode2StatusNameSerializer.class)
    private Integer status = UserStatusEnums.NORMAL.getStatusCode();
}
