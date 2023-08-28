package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haruka
 * @since 2023-08-28
 */
public interface IUserService extends IService<User> {
    public User login(User user);

}
