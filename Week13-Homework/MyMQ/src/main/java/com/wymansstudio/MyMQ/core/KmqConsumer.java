package com.wymansstudio.MyMQ.core;

import org.springframework.stereotype.Component;

@Component
public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker) {

        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> poll() {

        //return kmq.poll(timeout);
        return kmq.poll();
    }

}
