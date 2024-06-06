package com.example.demo.pojo;

import lombok.Data;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName LogDetails.java
 * @Description TODO 日志信息实体类
 * @createTime 2024年06月06日 12:52:00
 */

@Data
public class LogDetails {
    private long startTime; //开始处理时间
    private long processTime; // 结束处理时间
    private long endTime;//请求结束时间
    private int code; // 返回的http状态码
    private String handle;//具体处理器
    private String method;//请求方法
    private String uri;//请求的uri
    private String remoteAddr;//发起请求的对端地址
    private String exception;// 发生的异常类
    private String user;// security当前登录的用户信息
}
