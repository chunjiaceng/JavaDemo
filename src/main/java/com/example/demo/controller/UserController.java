package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.BaseResponse;
import com.example.demo.common.RespGenerator;
import com.example.demo.entity.User;
import com.example.demo.entity.request.UserBO;
import com.example.demo.entity.response.UserQuery;
import com.example.demo.service.IUserService;
import com.example.demo.utils.TokenUtils;
import com.example.demo.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "/UserController")
public class UserController {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    IUserService iUserServicel;

    @ApiOperation(value = "登录")
    @GetMapping("/login")
    public BaseResponse<User> login(@RequestParam(value = "name") String name, @RequestParam(value = "passWord") String passWord) throws JsonProcessingException {
        //包装token
        User user = new User();
        user.setName(name);
        user.setPassword(passWord);
        String token = TokenUtils.sign(user);
        redisUtils.set("token",token,30, TimeUnit.MINUTES);
        List<Object> list = Arrays.asList(token,iUserServicel.login(user));
        return RespGenerator.success(list);

    }

    @ApiOperation(value = "查询全部用户信息")
    @GetMapping("/query")
    public BaseResponse<List<User>> query(){
        List<User> list = iUserServicel.list();
        return RespGenerator.success(list);
    }

    @ApiOperation(value = "根据id删除指定用户信息")
    @DeleteMapping("/userById")
    public BaseResponse<Boolean> delete(@RequestBody Integer id){
        return RespGenerator.success(iUserServicel.delete(id));
    }

    @ApiOperation(value = "根据传入的User对象分页查询")
    @PostMapping("/page")
    public BaseResponse<IPage<User>> queryPage(@RequestBody UserQuery query){
        return RespGenerator.success(iUserServicel.queryPage(query));
    }

    @ApiOperation(value = "注册功能")
    @PostMapping("/register")
    public BaseResponse<User> register(@RequestBody UserBO user){
        User entity = new User();
        BeanUtils.copyProperties(user,entity);
        String token = TokenUtils.sign(entity);
        System.out.println("token为："+token);
        List<Object> list = Arrays.asList(token,iUserServicel.register(entity));
        return RespGenerator.success(list);
    }
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/user")
    public BaseResponse<Boolean> update(@RequestBody UserBO user){
        User entity = new User();
        BeanUtils.copyProperties(user,entity);
        return RespGenerator.success(iUserServicel.updateById(entity));
    }
}
