package com.example.Week8Homework.controller;

import com.example.Week8Homework.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShipController {

    private final ShipService shipService;

    @RequestMapping("/updateShip")
    public void updateShipperPhone(@RequestBody Long id){
        shipService.updateShippingInfo(id);
    }

}
