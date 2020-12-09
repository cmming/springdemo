package com.example.springdemo.repository;

import com.example.springdemo.dao.Country;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void add() {
        Country c1 = new Country();
        c1.setCode(86);
        c1.setName("中国");
        countryRepository.save(c1);

        Country c2 = new Country();
        c2.setCode(001);
        c2.setName("美国");
        countryRepository.save(c2);
    }
}