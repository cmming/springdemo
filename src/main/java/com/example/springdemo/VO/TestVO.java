package com.example.springdemo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: chenming
 * @Description: 解决前端传入字段不符合后端代码规范。
 * @Date: Create in 11:37 2020-09-22
 **/
@Data
public class TestVO {

    @JsonProperty("NAME")
    private String name;
}
