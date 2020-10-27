package com.example.springdemo.form;

import com.example.springdemo.utils.deserializer.UserNameJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 9:35 2020-09-15
 **/
@Data
public class UserForm {

    /**
     * TODO 请求数据转换会在NotBlank之前、在NotNull之后，不知道为什么
     */
    @JsonDeserialize(using = UserNameJsonDeserializer.class)
    @NotNull(message="名称必须填")
    @NotBlank(message="名称不能为空")
    private String name;

    @NotNull(message="年龄必须填")
//    @Pattern(regexp = "^[0-9]*[1-9][0-9]*$", message = "年龄必须为正整数")
    @Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
    private Integer age;
}
