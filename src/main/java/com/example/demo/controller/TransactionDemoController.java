package com.example.demo.controller;

import com.example.demo.common.BaseResponse;
import com.example.demo.service.IUserService;
import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haruka 曾春佳
 * @version 1.0.0
 * @ClassName TransactionDemoController.java
 * @Description TODO 事务测试类
 * @createTime 2024年05月29日 20:52:00
 */

@RestController
@Slf4j
public class TransactionDemoController {
    @Autowired
    TestService testService;
    @Transactional
    @RequestMapping("/transaction")
    public BaseResponse test(){
        try{
            testService.test();
            log.info("Ruby YES!!!!");
        }catch (Exception e){
            log.info("Ruby Catch");
            return  BaseResponse.error();

        }
        return  BaseResponse.success();

    }

}
