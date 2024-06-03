package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.Map;

/*
 * TODO 用户认证相关
 **/
public interface LoginService {
    // 登录方法
    public Map<String,Object> login(User user);

    public void logout();
}
