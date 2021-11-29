package com.wymansstudio.RedisDemo.controller;

import com.wymansstudio.RedisDemo.client.StockManagementServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisTest")
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StockManagementServiceClient stockManagementServiceClient;

    @GetMapping
    public String testRedis(){
        //Test Distributed Lock
        //Acquired global lock
        String lockValue = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", lockValue,60, TimeUnit.SECONDS);

        //Acquired lock successful
        if (lock) {

            String value = (String) redisTemplate.opsForValue().get("lock");
            if (StringUtils.isEmpty(value)){
                return "";
            }

            //update Stock
            stockManagementServiceClient.updateStock();


            System.out.println(value);
            if (lockValue.equals(value)) {
                redisTemplate.opsForValue().getAndDelete("lock");
            }
        }else{
            try{
                Thread.sleep(100);
                testRedis();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "";
    }
}
