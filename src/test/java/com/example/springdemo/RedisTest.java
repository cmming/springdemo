package com.example.springdemo;

import com.example.springdemo.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 21:36 2020/10/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("hello","hello");
        System.out.println("-----redis测试-----");
        System.out.println(redisTemplate.opsForValue().get("hello"));
        System.out.println("-----redis测试-----");
    }

    @Test
    public void testUser() {
        User user1 = new User();
        user1.setAge(18);
        user1.setName("clear");
        redisTemplate.opsForValue().set("user1",user1);
        System.out.println("-----redis测试-----");
        System.out.println(redisTemplate.opsForValue().get("user1"));
        System.out.println("-----redis测试-----");
    }
}

