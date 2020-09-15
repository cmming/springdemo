package com.example.springdemo.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 9:35 2020-09-15
 **/
@Data
public class UserForm {

    @NotNull(message="名称必须填")
    private String name;

    @NotNull(message="年龄必须填")
//    @Pattern(regexp = "^[0-9]*[1-9][0-9]*$", message = "年龄必须为正整数")
    @Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
    private Integer age;
}
