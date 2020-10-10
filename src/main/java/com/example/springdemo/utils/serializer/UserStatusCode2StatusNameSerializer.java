package com.example.springdemo.utils.serializer;

import com.example.springdemo.enums.UserStatusEnums;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author: chenming
 * @Description: 转换响应的数据格式：将用户状态转换为汉字 .
 * @Date: Create in 14:41 2020-10-09
 **/
public class UserStatusCode2StatusNameSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(UserStatusEnums.getValue(s));
    }
}
