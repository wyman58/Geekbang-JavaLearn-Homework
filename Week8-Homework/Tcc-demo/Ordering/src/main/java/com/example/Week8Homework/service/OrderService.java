package com.example.Week8Homework.service;

import com.example.Week8Homework.client.ShipClient;
import com.example.Week8Homework.entity.Order;
import com.example.Week8Homework.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation .HmilyTCC;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShipClient shipClient;

    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void addOrderAndUpdateShip(Long id){
        System.out.println(id);
        Order newOrder = new Order();
        newOrder.setOrderID(id);
        newOrder.setShipperID(1L);
        newOrder.setEmployeeID(1L);
        newOrder.setCustomerID(Double.valueOf(Math.random() * 10).longValue());
        newOrder.setOrderDate(LocalDate.now());
        orderRepository.save(newOrder);
        shipClient.updateShipping();
    }

    public void confirmOrderStatus(Order order) {
        //updateOrderStatus(order, OrderStatusEnum.PAY_SUCCESS);
        log.info("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus(Order order) {
        //updateOrderStatus(order, OrderStatusEnum.PAY_FAIL);
        log.info("=========进行订单cancel操作完成================");
    }

}
