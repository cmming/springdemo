package com.example.springdemo.repository;

import com.example.springdemo.dao.Book;
import com.example.springdemo.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存一本书 .
     */
    @Test
    public void addBook() {
        Book book = new Book();
        book.setName("西游记");
        User user = userRepository.findById(5).get();
        Book newBook = bookRepository.save(book);
        newBook.setUser(user);
        bookRepository.save(newBook);
    }

}