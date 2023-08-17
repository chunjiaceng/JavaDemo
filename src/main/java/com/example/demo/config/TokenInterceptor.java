package com.example.demo.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demo.utils.RedisUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    TokenUtils tokenUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws  Exception{

        // 跨域请求会首先发一个Option请求，直接返回正常状态并通过拦截器
        // 先放行第一次请求
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        JSONObject map = new JSONObject();

        //令牌建议是放在请求头中，获取请求头中令牌
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        response.setContentType("application/json,charset=utf-8");
        try{
            if (token != null){
                if (tokenUtils.verify(token)) System.out.println("通过拦截器");
                return tokenUtils.verify(token);
            }
            map.put("msg","token不能为空！");
        }catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg","token过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg","token算法不一致");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","token失效");
        }
        response.getWriter().append(map.toString());
        return false;
    }

}
