package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Haruka 曾春佳
 * @version 1.0.0
 * @ClassName TestService.java
 * @Description TODO
 * @createTime 2024年05月29日 20:53:00
 */

@Service
public class TestService {
    @Autowired
    IUserService userService;
    @Transactional
    public void test(){
        User user = new User();
        user.setName("111");
        userService.save(user);
        throw new RuntimeException();
    }
}
