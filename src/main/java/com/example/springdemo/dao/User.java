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
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * 响应数据格式转换(为了表面redis序列化由于值类型发生变化导致序列化异常，所以讲状态改为字符串)
     * TODO 或者有其他方式优化
     */
    @JsonSerialize(using = UserStatusCode2StatusNameSerializer.class)
    private String status = UserStatusEnums.NORMAL.getStatusCode();
}
