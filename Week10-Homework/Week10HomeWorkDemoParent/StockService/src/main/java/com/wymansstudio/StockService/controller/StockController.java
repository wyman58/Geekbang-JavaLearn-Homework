package com.wymansstudio.StockService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/updateStock")
    public String updateStock(){

        System.out.println("updateStock is called");
        Object currentValue = redisTemplate.opsForValue().get("stock");

        if(currentValue!=null && (Integer)currentValue > 0) {
            Long value = redisTemplate.opsForValue().decrement("stock");
            System.out.println("Remains" + value);
        }
        return "";
    }
}
