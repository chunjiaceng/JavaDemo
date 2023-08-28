package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.UserQuery;
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

    @Override
    public Boolean delete(Integer id) {
        return userMapper.deleteById(id) != 0;
    }

    @Override
    public IPage<User> queryPage(UserQuery query) {
        IPage<User> page = new Page<>();
        page.setCurrent(query.getPageNumber());
        page.setSize(query.getPageSize());
        page.setTotal(query.getTotal());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(StringUtils.isNotEmpty(query.getName()),User::getName,query.getName())
                .or()
                .eq(ObjectUtils.isNotNull(query.getAge()),User::getAge,query.getAge())
                .or()
                .eq(StringUtils.isNotEmpty(query.getPassword()),User::getPassword,query.getPassword());

        IPage<User> result = userMapper.selectPage(page,queryWrapper);
        return  result;
    }


}
