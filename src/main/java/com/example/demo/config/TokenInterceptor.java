package com.example.demo.config;

import com.example.demo.utils.RedisUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws  Exception{

        // 跨域请求会首先发一个Option请求，直接返回正常状态并通过拦截器
        // 先放行第一次请求
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        if (token != null){
            boolean result = redisUtils.verifyToken(token);
            if (result != false){
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setContentType("application/json,charset=utf-8");
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","未通过token校验");
            jsonObject.put("code","500");
            response.getWriter().append(jsonObject.toString());
            System.out.println("认证失败，未通过拦截器");

        }catch (Exception e){
            return  false;
        }
    return false;
    }

}
