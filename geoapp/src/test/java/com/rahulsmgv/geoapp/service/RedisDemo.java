package com.rahulsmgv.geoapp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisDemo {
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    @Disabled
    public void nameCheck(){
//        redisTemplate.opsForValue().set("name", "Mohit");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name "+ name);
    }
}
