/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.repository;

import com.example.springdemo.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据的持久层操作 .
 *
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
