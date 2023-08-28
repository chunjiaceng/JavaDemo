package com.example.demo.utils;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Data
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(final String key){
        return redisTemplate.opsForValue().get(key);
    }
    public boolean set(final String key,final String value){
        boolean result = false;
        try{
            redisTemplate.opsForValue().set(key,value);
            result = true;
            return result;
        }catch (Exception e){
            return result;
        }
    }

    public boolean set(final String key, final String value, long timeout, TimeUnit unit){
        boolean rs = false;
        try{
            redisTemplate.opsForValue().set(key,value,timeout,unit);
            return rs = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public boolean getAndSet(final String key,final String value){
        boolean result  = false;
        try{
            redisTemplate.opsForValue().getAndSet(key,value);
            return result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(final String key){
        boolean result = false;
        try{
            redisTemplate.delete(key);
            return result=true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
}
