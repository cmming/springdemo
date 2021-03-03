package com.example.springdemo.repository;

import com.example.springdemo.dao.IDCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDCardRepository extends JpaRepository<IDCard, String> {

    IDCard findByCardNum(String cardNum);

    IDCard deleteByCardNum(String cardNum);
}
