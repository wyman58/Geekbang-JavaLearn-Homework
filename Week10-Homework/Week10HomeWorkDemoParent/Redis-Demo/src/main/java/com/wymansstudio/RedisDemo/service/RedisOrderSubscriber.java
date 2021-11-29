package com.wymansstudio.RedisDemo.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisOrderSubscriber implements MessageListener {

    public static List<String> messageList = new ArrayList<String>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Order received: " + message.toString());
    }
}
