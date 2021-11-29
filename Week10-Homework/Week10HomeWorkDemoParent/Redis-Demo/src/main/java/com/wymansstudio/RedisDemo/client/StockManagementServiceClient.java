package com.wymansstudio.RedisDemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "StockService", url = "http://127.0.0.1:8090")
public interface StockManagementServiceClient {

    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public void updateStock();

}
