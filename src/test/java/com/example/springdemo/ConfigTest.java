package com.example.springdemo;

import com.example.springdemo.config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: chenming
 * @Description: 测试配置文件
 * @Date: Create in 16:42 2020/10/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ConfigTest {

    @Autowired
    private TestConfig testConfig;

    @Test
    public void testConfig() {
        System.out.println(testConfig.getName());
    }
}
