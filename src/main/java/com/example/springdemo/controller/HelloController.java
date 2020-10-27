package com.example.springdemo.controller;

import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.VO.TestVO;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.DemoException;
import com.example.springdemo.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/index")
    public String index() {
        return "hello";
    }

    /**
     * 测试前端传入的字段key为 NAME(不符合代码检测规范，使用@JsonProperty对字段进行重命名)
     * @param testVO
     * @return
     */
    @PostMapping("/testJsonProperty")
    public ResultVO testJsonProperty(@RequestBody TestVO testVO) {
        String name1 = testVO.getName();
        System.out.println(name1);
        return ResultVOUtil.success(testVO);
    }

    @GetMapping("/redis")
    public void testRedis(){
        redisTemplate.opsForValue().set("redisTest","redisTest");
    }

    @GetMapping("/testDemoException")
    public ResultVO testDemoException() {
        DemoException demoException = new DemoException(ResultEnum.NOT_FOUND.getCode(), ResultEnum.NOT_FOUND.getMessage());
        throw demoException;
    }
}
