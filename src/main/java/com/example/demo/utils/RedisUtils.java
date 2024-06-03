package com.example.demo.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Data
public class RedisUtils {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    public  <T>T get(final String key,Class<T> clazz){
        String s = redisTemplate.opsForValue().get(key);
        T t = JSON.parseObject(s, clazz);
        return t;
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

    public boolean set(final String key,final Object value){
        boolean result = false;
        String jsonString = JSON.toJSONString(value);
        try{
            redisTemplate.opsForValue().set(key,jsonString);
            result = true;
            return result;
        }catch (Exception e){
            return result;
        }
    }
    public boolean set(final String key, final Object value, long timeout, TimeUnit unit){
        boolean rs = false;
        String jsonString = JSON.toJSONString(value);
        try{
            redisTemplate.opsForValue().set(key,jsonString,timeout,unit);
            return rs = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
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
