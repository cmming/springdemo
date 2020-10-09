package com.example.springdemo.controller;

import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.dao.User;
import com.example.springdemo.form.UserForm;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * TODO 获取用户中
     * 获取用户列表.
     * @param pageNum
     * @param pageSize
     * @return
     */
//    @Cacheable(value = "user", key = "123")
    @GetMapping("")
    public ResultVO index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        // Pageable pageable
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<User> result = userRepository.findAll(pageable);
        return ResultVOUtil.success(result);
    }

    @PostMapping("")
    public ResultVO store(@Valid @RequestBody UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        User result = userRepository.save(user);
        return  ResultVOUtil.success(result);
    }
}
