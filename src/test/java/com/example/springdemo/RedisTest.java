package com.example.springdemo;

import com.example.springdemo.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void testUserObj() {
        User user1 = new User();
        user1.setAge(18);
        user1.setName("陈明");
        redisTemplate.opsForValue().set("user1",user1);
        System.out.println("-----redis测试-----");
        System.out.println(redisTemplate.opsForValue().get("user1"));
        System.out.println("-----redis测试-----");
    }

    @Test
    public void testList() {
        User user1 = new User();
        user1.setAge(18);
        user1.setName("clear");
        redisTemplate.opsForList().leftPush("list", "chmi");
        redisTemplate.opsForList().leftPush("list", "chmi2");
        System.out.println("-----redis set测试-----");
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        System.out.println("-----redis set测试-----");
    }

    @Test
    public void testSet() {
        redisTemplate.opsForSet().add("set", "A", "B", "C");
        System.out.println("-----redis set测试-----");
        System.out.println(redisTemplate.opsForSet().members("set"));
        System.out.println("-----redis set测试-----");
    }

    @Test
    public void testZSet() {
        redisTemplate.opsForZSet().add("zset", "张三", 10);
        redisTemplate.opsForZSet().add("zset", "李四", 8);
        redisTemplate.opsForZSet().add("zset", "王五", 10);
        redisTemplate.opsForZSet().incrementScore("zset","王五", 6);

        System.out.println("-----redis set测试-----");
        System.out.println(redisTemplate.opsForZSet().range("zset", 0, -1));
        System.out.println("-----redis set测试-----");
    }

    @Test
    public void testMap() {
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "chenming");
        map1.put("age", 27);

        redisTemplate.opsForHash().putAll("map1", map1);
        System.out.println("-----redis hash测试-----");
        System.out.println(redisTemplate.opsForHash().get("map1", "name"));
        System.out.println(redisTemplate.opsForHash().get("map1", "age"));
        System.out.println("-----redis hash测试-----");
    }

    @Test
    public void putSameKeyToMap() {
        Map<String, User> map1 = new HashMap<>();
        User user1 = new User();
        user1.setAge(1);
        user1.setName("chmi1");
        map1.put("1", user1);

        User user2 = new User();
        user2.setAge(2);
        user2.setName("chmi2");
        map1.put("2", user2);

        map1.forEach((k, v) -> {
            if (k.equals("2")) {
                v.setName("chmi3");
            }
        });

        List<User> l1 = new ArrayList<>();
        l1.add(user1);
        l1.add(user2);

        l1.forEach((v) -> {
            v.setName("add" + v.getName());
            return;
        });
//        for (User u : l1) {
//            u.setName("add-" + u.getName());
//            break;
//        }

        System.out.println(map1);
        System.out.println(l1);
    }
}

