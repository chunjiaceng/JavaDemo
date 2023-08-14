package com.example.demo;

import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void redisTest() throws  Exception{
        User user = new User("haruka","123456");
        ValueOperations<String,User> ops = redisTemplate.opsForValue();
        ops.set("user1",user);
        boolean exit = redisTemplate.hasKey("user1");
        if (exit){
            System.out.println("数据库已经存入对应数据");
            User getUser = (User) redisTemplate.opsForValue().get("user1");
            System.out.println("数据为：" + getUser);
        }
    }

}
