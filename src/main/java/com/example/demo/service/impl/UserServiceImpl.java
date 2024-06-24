package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.BaseExceptionEnum;
import com.example.demo.pojo.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haruka
 * @since 2023-08-28
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(User::getUsername,username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null){
            throw  new BaseException(BaseExceptionEnum.BODY_NULL);
        }
        User user = userMapper.selectById(id);
        if (user == null){
            throw new BaseException(BaseExceptionEnum.USER_NOT_EXIT);
        }
        return userMapper.deleteById(id) != 0;
    }


    @Override
    public User register(User user) {
        if (StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword())){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper
                    .eq(StringUtils.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());

            User one = userMapper.selectOne(queryWrapper);
            if (!ObjectUtils.isEmpty(one)){
                throw new BaseException(BaseExceptionEnum.USER_EXSIT);
            }
            int rs = userMapper.insert(user);
            if (rs>0){
                return userMapper.selectOne(queryWrapper);
            }
            throw new BaseException(BaseExceptionEnum.SERVER_ERROR);
        }
        throw  new BaseException(BaseExceptionEnum.BODY_NULL);

    }

}
