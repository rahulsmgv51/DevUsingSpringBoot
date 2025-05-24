package com.rahulsmgv.geoapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T getData(String key, Class<T> entityClass){
        try{
            Object obj = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(obj.toString(), entityClass);
        }catch (Exception e){
            log.info("Exception Occured While Fetching Data from Cache {}", e);
            return null;
        }
    }


    public void setData(String key, Object obj, Long ttl){
        try{
            ObjectMapper  mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(obj);
           redisTemplate.opsForValue().set(key, jsonValue, ttl);
        }catch (Exception e){
            log.info("Exception Occured While Fetching Data from Cache {}", e);
        }
    }
}
