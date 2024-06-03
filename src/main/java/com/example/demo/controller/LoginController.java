package com.example.demo.controller;

import com.example.demo.common.BaseResponse;
import com.example.demo.pojo.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description TODO 用户认证相关接口
 * @createTime 2024年06月02日 18:26:00
 */

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public BaseResponse<Map<String,Object>> login(@RequestBody User user){
        return BaseResponse.success(loginService.login(user));
    }
    @RequestMapping("/logout")
    public BaseResponse<Boolean> logout(){
        loginService.logout();
        return BaseResponse.success(true);
    }

}
