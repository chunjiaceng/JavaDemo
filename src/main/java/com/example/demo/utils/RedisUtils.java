package com.example.demo.utils;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@Data
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean verifyToken(String token){
        if (redisTemplate.opsForValue().get(token) != null) {
            System.out.println("该token为："+ redisTemplate.opsForValue().get(token));
            return  true;
        }
        System.out.println("查无此token，请再试一次");
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        ops.set(token, String.valueOf(Math.random()));
        return false;
    }
}
