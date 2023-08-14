package com.example.demo.controller;


import com.example.demo.pojo.User;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@RestController
@Api(tags = "/UserController")
public class UserController {

    @PostMapping("/user")
    public User getUser(){
        return new User("haruka","21");
    }

    @ApiOperation(value = "欢迎接口",httpMethod = "GET",notes = "这是欢迎接口的说明")
    @GetMapping("/hello")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",defaultValue = "hedon",paramType = "query",dataType="String",required = true),
            @ApiImplicitParam(name = "password",value = "密码",defaultValue = "hedonpassword",paramType = "query",dataType="String",required = true)
    })
    public String hello(String username,String password){
        return "HelloWorld, your name="+ username + ", password = "+password;
    }


}
