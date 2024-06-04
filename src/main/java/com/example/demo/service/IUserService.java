package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.pojo.User;
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
    public User findUserByName(String username);


    public Boolean delete(Long id);
    public User register(User user);
}
