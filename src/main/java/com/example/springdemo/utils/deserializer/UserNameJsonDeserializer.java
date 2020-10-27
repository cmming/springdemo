package com.example.springdemo.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @Author: chenming
 * @Description: 格式化请求数据(为所有的用户名添加auto-add) .
 * @Date: Create in 15:07 2020-10-09
 **/
public class UserNameJsonDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return "auto-add-" + jsonParser.getText();
    }
}
