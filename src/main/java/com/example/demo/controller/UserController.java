package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.BaseResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.UserQuery;
import com.example.demo.service.IUserService;
import com.example.demo.utils.TokenUtils;
import com.example.demo.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("token为："+token);
        return BaseResponse.success(token,iUserServicel.login(user));

    }

    @ApiOperation(value = "查询全部用户信息")
    @GetMapping("/query")
    public BaseResponse<List<User>> query(){
        List<User> list = iUserServicel.list();
        return  BaseResponse.success(list);
    }

    @ApiOperation(value = "根据id删除指定用户信息")
    @DeleteMapping("/userById")
    public BaseResponse<Boolean> delete(@RequestBody Integer id){
        return BaseResponse.success(iUserServicel.delete(id));
    }

    @ApiOperation(value = "根据传入的User对象分页查询")
    @PostMapping("/page")
    public BaseResponse<IPage<User>> queryPage(@RequestBody UserQuery query){
        return BaseResponse.success(iUserServicel.queryPage(query));
    }
}
