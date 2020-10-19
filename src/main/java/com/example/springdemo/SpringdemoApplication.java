package com.example.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// TODO 使用ServletComponentScan扫描filter，多个filter的执行顺序无法控制，是按照类名来实现自定义Filter顺序，具体是怎么依据类名进行排序我也不清楚。
// @ServletComponentScan
@SpringBootApplication
public class SpringdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }

}
