package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.BaseResponse;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserQuery;
import com.example.demo.service.IUserService;
import com.example.demo.utils.RedisUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "/UserController")
public class UserController {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    IUserService iUserServicel;




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

    @ApiOperation(value = "注册功能")
    @PostMapping("/register")
    public BaseResponse<User> register(@RequestBody User user){
        User entity = new User();
        entity.setUsername(user.getUsername());
        String encode = new BCryptPasswordEncoder().encode(user.getPassword());
        entity.setPassword(encode);
        return BaseResponse.success(iUserServicel.register(entity));
    }
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/user")
    public BaseResponse<Boolean> update(@RequestBody User user){
        return BaseResponse.success(iUserServicel.updateById(user));
    }
}
