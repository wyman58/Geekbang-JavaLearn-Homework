package com.wymansstudio.RedisDemo.service;

public interface OrderPublisher {
    void publish(String order);
}
