package com.example.springdemo.controller;

import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.annotation.AuditLog;
import com.example.springdemo.annotation.DemoUser;
import com.example.springdemo.dao.User;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.form.UpdateUserForm;
import com.example.springdemo.form.UserForm;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.utils.JsonUtils;
import com.example.springdemo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
@Slf4j
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
    // @Cacheable(value = "userPage", key = "#pageSize + #pageNum")
    @GetMapping("")
    @AuditLog(auditItemId = "user@list", logContent = "'用戶列表信息查詢，页数为：' + #pageNum + '，条数为：' + #pageSize",
            resultContent = "'响应值为' + #ResultVO")
    public ResultVO index(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                          @ModelAttribute("test") final String testString, @ModelAttribute("test1") final String testString1) {
        // Pageable pageable
        System.out.println(testString);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<User> result = userRepository.findAll(pageable);
        log.info("用户列表信息：{}", JsonUtils.toJson(result));
        return ResultVOUtil.success(result);
    }

    @PostMapping("")
    @AuditLog(auditItemId = "user@store", logContent = "'请求的参数信息为：' + #userForm")
    public ResultVO store(@Valid @RequestBody UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        User result = userRepository.save(user);
        return  ResultVOUtil.success(result);
    }

    /**
     * 获取用户的时候，将用户信息保存到redis中 .
     * @param id
     * @return
     */
//    @Cacheable(value = "userOne", key = "#id")
    @GetMapping("/one")
    public ResultVO get(@RequestParam(value = "id") Integer id) {
        User result = userRepository.findById(id).get();
        return  ResultVOUtil.success(result);
    }

    @DeleteMapping("")
    /**
     * 删除用户的时候，同时删除redis中的缓存
     */
    @CacheEvict(value = "userOne", key = "#id")
    public ResultVO deleteUser(@RequestParam(value = "id") Integer id) {
        userRepository.deleteById(id);
        return  ResultVOUtil.success();
    }

    @PutMapping("")
    /**
     * 删除用户的时候，同时更新redis中的缓存
     */
    @CachePut(value = "userOne", key = "#user.id")
    public ResultVO updateUser(@Valid @RequestBody UpdateUserForm user) {
        Optional<User> oldUserOptional = userRepository.findById(user.getId());
        if (!oldUserOptional.isPresent()) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_FOUND);
        }
        User oldUser = oldUserOptional.get();
        BeanUtils.copyProperties(user, oldUser, "id", "status");
        userRepository.save(oldUser);
        return  ResultVOUtil.success(oldUser);
    }

    @GetMapping("/oneTest")
    public ResultVO oneTest1(@DemoUser User user) {
        User result = user;
        return  ResultVOUtil.success(result);
    }

    @GetMapping("/testUserConverter")
    public ResultVO testUserConverter(User user) {
        User result = user;
        return  ResultVOUtil.success(result);
    }
}
