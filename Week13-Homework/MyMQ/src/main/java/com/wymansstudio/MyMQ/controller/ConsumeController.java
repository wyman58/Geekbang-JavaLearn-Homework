package com.wymansstudio.MyMQ.controller;

import com.wymansstudio.MyMQ.core.KmqConsumer;
import com.wymansstudio.MyMQ.core.KmqMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConsumeController {

    @Autowired
    KmqConsumer kmqConsumer;

    @RequestMapping(path = "/consume/{topic}")
    public void consume(@PathVariable final String topic){
        kmqConsumer.subscribe(topic);
        KmqMessage kmqMessage = kmqConsumer.poll();
        if (kmqMessage != null) {
            log.info((String) kmqMessage.getBody());
        }
    }
}
