package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.LoginUser;
import com.example.demo.pojo.User;
import com.example.demo.service.LoginService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName LoginServiceImpl.java
 * @Description TODO 用户认证相关服务类
 * @createTime 2024年06月03日 14:30:00
 */

@Service
public class LoginServiceImpl implements LoginService {
    // 对认证接口进行重写，请求login方法时将jwt令牌返回给前端
    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    RedisUtils redisUtils;
    @Resource
    JWTUtils jwtUtils;

    /*
     * @description: TODO 用户的登录接口，登录成功后返回jwt
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/3 15:00
     * @param: [user] 用户的登录信息
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> login(User user) {
        // 给authenticationToken对象写入用户名和密码，再使用 UsernamePasswordAuthenticationToken 对象通过AuthenticationFilter
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        //authenticationManager.authenticate(authenticationToken);返回的则是自定义登录方法中返回的UserDetails对象 UserDetails对象实现了Authentication接口
        Authentication authenticate  = authenticationManager.authenticate(authenticationToken);
        // 密码核对不通过抛出异常
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("密码错误");
        }

        // 验证通过， 通过Spring Security，生成jwt给前端
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        //根据ID生成jwt
        String jwt = jwtUtils.createJWT(id);
        // 直接将用户信息传入Redis中
        redisUtils.set("login:" + id, loginUser,604800, TimeUnit.SECONDS);

        // 再将jwt返回给前端
        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        return map;


    }
    /*
     * @description: TODO 退出登录
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/3 17:25
     * @param: []
     * @return: void
     **/
    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser =  (LoginUser) authentication.getPrincipal();
        String user_id = loginUser.getUser().getId().toString();
        redisUtils.delete("login:"+user_id);
        return;
    }
}
