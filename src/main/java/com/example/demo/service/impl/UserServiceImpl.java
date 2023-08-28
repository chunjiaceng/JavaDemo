package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haruka
 * @since 2023-08-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(StringUtils.isNotEmpty(user.getName()),User::getName,user.getName())
                .eq(StringUtils.isNotEmpty(user.getPassword()),User::getPassword,user.getPassword());
        User one = userMapper.selectOne(queryWrapper);
        return one;
    }

}
