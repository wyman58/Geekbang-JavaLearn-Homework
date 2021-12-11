package com.wymansstudio.Kafka.Demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerDemo {

    private static final Logger log= LoggerFactory.getLogger(KafkaListenerDemo.class);

    //声明consumerID为demo，监听topicName为topic.quick.demo的Topic
    @KafkaListener(id = "demo", topics = "topic.quick.demo")
    public void listen(String msgData) {
        log.info("demo receive : "+msgData);
    }
}
