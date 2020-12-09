package com.example.springdemo.repository;

import com.example.springdemo.dao.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAllByCodeIn(List<Integer> codes);
}
