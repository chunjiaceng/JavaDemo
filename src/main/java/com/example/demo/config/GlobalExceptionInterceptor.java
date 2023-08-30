package com.example.demo.config;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);
    @ExceptionHandler(value = BaseException.class)
    public BaseResponse<Map<String,Object>> baseExceptionHandler(BaseException e){
        Map<String,Object> rs = new HashMap<>();
        rs.put("code",e.getCode());
        rs.put("message",e.getMessage());
        logger.error("发生异常！异常原因是：",e.getMessage());
        return BaseResponse.error(rs);
    }
    @ExceptionHandler(value = NullPointerException.class)
    public BaseResponse<Map<String,Object>> nullPointExceptionHandle(NullPointerException e){
        Map<String,Object> rs = new HashMap<>();
        rs.put("message","空指针异常");
        logger.error("发生异常！异常原因是：",rs.get("message"));
        return BaseResponse.error(rs);

    }
}
