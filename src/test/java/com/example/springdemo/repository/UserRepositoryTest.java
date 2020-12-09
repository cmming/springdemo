package com.example.springdemo.repository;

import com.example.springdemo.dao.Book;
import com.example.springdemo.dao.Country;
import com.example.springdemo.dao.IDCard;
import com.example.springdemo.dao.User;
import com.example.springdemo.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void saveOne() {
        IDCard idCard = new IDCard();
        idCard.setCardNum("421126199304275134");
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        user.setPassword("123456");
        user.setIdCard(idCard);
        userRepository.save(user);
    }

    @Test
    public void addIDCardForUser() {
        IDCard idCard = new IDCard();
        idCard.setCardNum("421126199304275112");
        User user = userRepository.findById(12).get();
        user.setIdCard(idCard);
        userRepository.save(user);
    }

    @Test
    public void findOne() {
        User user = userRepository.findById(1).get();
        System.out.println(JsonUtils.toJson(user));
    }

    @Test
    public void updateOne() {
        User user = userRepository.findById(31).get();
        user.setName("张三修改");
        userRepository.save(user);
    }

    /**
     * 删除操作：在关系维护表中进行
     */
    @Test
    public void deleteOne() {
        userRepository.deleteById(32);
    }

    @Test
    public void saveOneBook() {
        User user = userRepository.findById(1).get();
        Book book = new Book();
        book.setName("水浒传");
        Book newBook = bookRepository.save(book);
        newBook.setUser(user);
        bookRepository.save(newBook);
    }

    @Test
    public void getUserBooks() {
        List<Book> books = userRepository.findById(1).get().getBooks();
        System.out.println(JsonUtils.toJson(books));
    }

    /**
     * 用户的图书管理 .
     */
    @Test
    public void testDeleteOneBook() {
        User user = userRepository.findById(12).get();
        List<Book> oldBooks = user.getBooks();
        List<Book> userSelect = new ArrayList<>();
        Book book1 = new Book();
        book1.setName("西游记8");
//        Book nBook1 = bookRepository.save(book1);
//        nBook1.setUser(user);
//        bookRepository.save(nBook1);
        userSelect.add(book1);

        Book book2 = new Book();
        book2.setName("红楼梦8");
//        Book nBook2 = bookRepository.save(book2);
//        nBook2.setUser(user);
//        bookRepository.save(nBook2);
        userSelect.add(book2);

        // 新增加的，在userSet中存在，但是oldBooks中不存在
        // 相同的数据
        List<Book> sameBooks = new ArrayList<>();
        for (Book book : userSelect) {
            for (Book oldBook : oldBooks) {
                if (book.getName().equals(oldBook.getName())) {
                    sameBooks.add(book);
                }
            }
        }
        // 新增的数据
        List<Book> addBooks = new ArrayList<>();
        for (Book book : userSelect) {
            int flag=0;
            for (Book sameBook : sameBooks) {
                if (book.getName().equals(sameBook.getName())) {
                    flag = 1;
                }
            }
            if(flag==0){
                addBooks.add(book);
            }
        }
        // 删除的
        List<Book> delBooks = new ArrayList<>();
        for (Book book : oldBooks) {
            int flag=0;
            for (Book sameBook : sameBooks) {
                if (book.getName().equals(sameBook.getName())) {
                    flag = 1;
                }
            }
            if(flag==0){
                delBooks.add(book);
            }
        }

        //添加的为addBooks，删除的为delBooks
        for ( Book delBook : delBooks) {
//            delBook.setUser(null);
            bookRepository.deleteById(delBook.getId());
        }

        for ( Book addBook : addBooks) {
            Book newAddBook = bookRepository.save(addBook);
            newAddBook.setUser(user);
            bookRepository.save(newAddBook);
        }

    }

    /**
     * 为用户添加国籍
     * 通过关系维护方添加角色和用户的关系
     */
    @Test
    public void setCountryForUser() {
        User user = userRepository.findById(38).get();
        List<Integer> codes = new ArrayList<>();
        codes.add(86);
        codes.add(1);
        List<Country> countries = countryRepository.findAllByCodeIn(codes);
        user.setCountries(countries);
//        userRepository.saveAndFlush(user);
        userRepository.save(user);
    }

    /**
     * 会将用户的书籍、身份证同时删除，减少废数据（为了安全也可以采用软删除的方式进行删除）
     */
    @Test
    public void deleteUser() {
        userRepository.deleteById(36);
    }
}