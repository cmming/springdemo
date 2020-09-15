package com.example.springdemo.controller;

import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.dao.User;
import com.example.springdemo.form.UserForm;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResultVO index() {
        // Pageable pageable
        Pageable pageable = PageRequest.of(0, 10);
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
