package com.example.demo.service;

import com.example.demo.exception.BaseException;
import com.example.demo.exception.BaseExceptionEnum;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.pojo.LoginUser;
import com.example.demo.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Resource
    MenuMapper menuMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);
        if (user == null){
            throw new BaseException(BaseExceptionEnum.USER_NOT_EXIT);
        }

        // TODO 用户权限的注入
        // 通过多表联查查询目前用户角色属性下的所有权限标识符
        List<String> permissions = menuMapper.selectPremsByUserId(user.getId());
        return new LoginUser(user,permissions);
    }
}
