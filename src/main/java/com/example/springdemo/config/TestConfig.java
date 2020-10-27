package com.example.springdemo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.constraints.Email;

/**
 * @Author: chenming
 * @Description:用于javaBean测试springboot配置设置(jsr303校验使用 @Validated和属性上设置规则注解)
 * 1、application.properties文件覆盖属性 /root/config > /root > classpath/config > classpath  (classpath就是resources路径) 优先级从高到低
 * 2、多环境依赖切换 spring.profiles.active
 * @Date: Create in 16:25 2020/10/17
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
//@Validated
public class TestConfig {
//    @Email
    private String name;
    private Boolean isString;
}
