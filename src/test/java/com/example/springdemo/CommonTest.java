package com.example.springdemo;

import com.example.springdemo.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: chenming
 * @Description: 测试日常常见问题 .
 * @Date: Create in 21:36 2020/10/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CommonTest {

    @Test
    public void groupByKey() {
        Map<String,Integer>SataMap=new HashMap<>();
        SataMap.put("A",1);
        SataMap.put("b",3);
        SataMap.put("c",2);
        SataMap.put("D",4);
        SataMap.put("e",2);
        SataMap.put("f",4);
        SataMap.put("G",3);
        SataMap.put("h",2);

        Map<Integer, List<Map.Entry<String,Integer>>>result= SataMap.entrySet().stream().collect(Collectors.groupingBy(c -> c.getValue()));
        System.out.println(SataMap);
        System.out.println(result);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(10);
        user1.setName("chmi1");
        users.add(user1);
        User user2 = new User();
        user2.setAge(10);
        user2.setName("chmi2");
        users.add(user2);
        User user3 = new User();
        user3.setAge(8);
        user3.setName("chmi3");
        users.add(user3);

        Map<Integer, List<User>> collect = users.stream().collect(Collectors.groupingBy(User::getAge));

        System.out.println(users);
        System.out.println(collect);
    }

    @Test
    public void sumListByKey() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(10);
        user1.setName("chmi1");
        users.add(user1);
        User user2 = new User();
        user2.setAge(10);
        user2.setName("chmi2");
        users.add(user2);
        User user3 = new User();
        user3.setAge(8);
        user3.setName("chmi3");
        users.add(user3);

        //求和
        Long sum= users.stream().mapToLong(User::getAge).sum();
        System.out.println(sum);

        List<BigDecimal> items = Arrays.asList(BigDecimal.ONE,BigDecimal.valueOf(1.5),BigDecimal.valueOf(100));
        BigDecimal ad = items.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(ad);
    }
}

