package com.wymansstudio.MyMQ.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KmqProducer {

    private KmqBroker broker;

    public KmqProducer(KmqBroker broker) {

        this.broker = broker;
    }

    public boolean send(String topic, KmqMessage message) {
        Kmq kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");

        return kmq.send(message);
    }
}
