package com.example.demo.service;

import com.example.demo.pojo.LoginUser;
import com.example.demo.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName LoginUserDetailService.java
 * @Description TODO Security自定义用户登录验证
 * @createTime 2024年06月02日 17:47:00
 */

@Service
public class LoginUserDetailService implements UserDetailsService {
    @Resource
    IUserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);
        if (user == null){
            throw new UsernameNotFoundException("not User");
        }

        // TODO 用户权限的注入

        return new LoginUser(user);
    }
}
