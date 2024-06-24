package com.example.demo.common;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.BaseExceptionEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName AuthenticationEntryPointImpl.java
 * @Description TODO 自定义Security 认证异常处理对象
 * @createTime 2024年06月04日 17:12:00
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String result =JSON.toJSONString(BaseResponse.error(new BaseException(BaseExceptionEnum.AUTHENTICATION_ERROR)));
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        // 获取响应对象的输出流
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();//刷新输出流
        // 关闭流
        outputStream.close();
    }
}
