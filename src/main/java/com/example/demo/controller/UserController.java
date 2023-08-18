package com.example.demo.controller;


import com.example.demo.config.TokenInterceptor;
import com.example.demo.utils.TokenUtils;
import com.example.demo.pojo.User;
import com.example.demo.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "/UserController")
public class UserController {
    @Autowired
    TokenInterceptor tokenInterceptor;
    @Autowired
    RedisUtils redisUtils;

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

    @GetMapping("token")
    @ApiOperation(httpMethod = "GET" ,notes = "这是token的验证接口", value = "token接口")
    public void getToken(){
    }
    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "passWord") String passWord) throws JsonProcessingException {
        //包装token
        User user = new User();
        user.setName(name);
        user.setPassword(passWord);
        String token = TokenUtils.sign(user);
        System.out.println("token为："+token);
        return token;

    }

}
