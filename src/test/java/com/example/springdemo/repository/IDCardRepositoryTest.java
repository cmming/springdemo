package com.example.springdemo.repository;

import com.example.springdemo.dao.IDCard;
import com.example.springdemo.dao.User;
import com.example.springdemo.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IDCardRepositoryTest {

    @Autowired
    private IDCardRepository idCardRepository;

    /**
     * 关系被维护段，不能将关系保存。所以还是要在关系维护端去执行保存操作。
     * 总之只要存在修改外键的操作，都应该在关系维护表的实体中执行
     */
    @Test
    public void save() {
        IDCard idCard = new IDCard();
        idCard.setCardNum("421126199304275135");
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        user.setPassword("123456");
        idCard.setUser(user);
        idCardRepository.save(idCard);
    }

    @Test
    public void updateOne() {
        IDCard idCard = idCardRepository.findByCardNum("421126199304275135");
        idCard.getUser().setName("陈明123132");
        idCardRepository.save(idCard);
//        System.out.println(JsonUtils.toJson(idCard));
    }

    @Test
    public void findOne() {
        IDCard idCard = idCardRepository.findByCardNum("421126199304275134");
        System.out.println(JsonUtils.toJson(idCard));
    }

    @Test
    public void deleteOne() {
        idCardRepository.deleteByCardNum("421126199304275135");
    }
}