package com.example.springdemo.utils;

import com.example.springdemo.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: https://github.com/kevinhwu/spring-boot-example/blob/master/springboot-exception-demo/src/main/java/com/qikegu/demo/common/util/MiscUtil.java
 * @Description: 辅助类
 * @Date: Create in 10:23 2020-09-15
 **/
@Slf4j
public class MiscUtil {

    static public ResultVO getValidateError(BindingResult bindingResult) {

        if(bindingResult.hasErrors() == false)
            return null;

        Map<String,String> fieldErrors = new HashMap<String, String>();

        for(FieldError error : bindingResult.getFieldErrors()){
            fieldErrors.put(error.getField(), error.getCode() + "|" + error.getDefaultMessage());
        }

//        Result ret = new Result(422, "输入错误"); //rfc4918 - 11.2. 422: Unprocessable Entity

        return ResultVOUtil.error(HttpStatus.UNPROCESSABLE_ENTITY.value(),HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), fieldErrors);
    }
}
