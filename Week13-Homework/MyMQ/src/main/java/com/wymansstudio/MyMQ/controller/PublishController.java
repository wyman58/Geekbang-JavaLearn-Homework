package com.wymansstudio.MyMQ.controller;

import com.wymansstudio.MyMQ.core.KmqBroker;
import com.wymansstudio.MyMQ.core.KmqMessage;
import com.wymansstudio.MyMQ.core.KmqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
public class PublishController {

    @Autowired
    KmqProducer kmqProducer;

    @Autowired
    KmqBroker kmqBroker;

    @PostMapping(path = "/publish/{topic}/{message}")
    public void publish(@PathVariable final String topic, @PathVariable final String message){
        KmqMessage<String> kmqMessage = new KmqMessage(new HashMap<>(), message);
        kmqProducer.send(topic, kmqMessage);
    }

    @PostMapping(path = "/topic/{topic}")
    public void createTopic(@PathVariable final String topic){
        kmqBroker.createTopic(topic);
        //log.info(kmqBroker.findKmq(topic).toString());
    }
}
