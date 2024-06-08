package com.example.demo;

import com.example.demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class DemoApplicationTests {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void redisTest() throws  Exception{
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8082", String.class);
        System.out.println(response.getBody());
    }
    @Test
    public void webFluxTest(){
        Flux.just(5,4,3,2,1,0).filter(n -> n<4).take(3).doOnNext(n -> log.info("doOnNext: {}",n))
                .doOnComplete(()-> log.info("Complete!"))
                .doOnEach(n -> log.info("doOnEach: {} , {}",n.getType(),n.get()))
                .map(n -> n*n)
                .subscribe(n-> log.info("Subscribe: {}", n));

    }

}
