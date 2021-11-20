package com.example.Week8Homework.client;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "ship-service")
public interface ShipClient {

    @Hmily
    @RequestMapping("ship-service/updateShip")
    void updateShipping();

}
