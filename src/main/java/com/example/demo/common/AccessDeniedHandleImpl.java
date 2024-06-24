package com.example.demo.common;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.BaseExceptionEnum;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName AccessDeniedHandleImpl.java
 * @Description TODO 自定义Security 授权异常处理对象
 * @createTime 2024年06月04日 17:42:00
 */

@Component
public class AccessDeniedHandleImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        String result = JSON.toJSONString(BaseResponse.error(new BaseException(BaseExceptionEnum.ACCREDIT_ERROR)));
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
