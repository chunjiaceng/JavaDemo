package com.example.demo.filter;

import com.example.demo.pojo.LoginUser;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName JwtAuthenticationFilter.java
 * @Description TODO 获取请求中JWT Token信息接口
 * @createTime 2024年06月03日 16:33:00
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Resource
    JWTUtils jwtUtils;
    @Resource
    RedisUtils redisUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中携带的token信息
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            // 没有携带Token信息，直接放行由后续的Security Filter进行拦截
            // 直接放行当后续响应的时候也可以直接前往下一个Filter
            filterChain.doFilter(request,response);
            return;
        }
        String user_id;
        // 将token解析为User_id
        try {
            Claims claims = jwtUtils.parseJWT(token);
            user_id = claims.getSubject();

        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }


        // 从Redis中返回Authenticate信息
        String key = "login:"+user_id;
        LoginUser loginUser = redisUtils.get(key, LoginUser.class);

        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录！");
        }


        // 将获取到的Authenticate信息写入SecurityContextHolder中
         //1. 先封装一个authenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        // TODO 封装该用户单当前的权限信息

        // 2. 获取当前Secutiry上下文对象。装入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行

        filterChain.doFilter(request,response);

    }
}
