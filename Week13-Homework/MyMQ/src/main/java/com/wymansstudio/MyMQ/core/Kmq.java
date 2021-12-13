package com.wymansstudio.MyMQ.core;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        //this.queue = new LinkedBlockingQueue(capacity);
        this.queue = new ArrayList<KmqMessage>(capacity);
        this.headIndex = 0;
        this.tailIndex = 0;
    }

    private String topic;

    private int capacity;

    //private LinkedBlockingQueue<KmqMessage> queue;
    private ArrayList<KmqMessage> queue;
    private int headIndex;
    private int tailIndex;

    public boolean send(KmqMessage message){

        //return queue.offer(message);
        //this.queue.set(tailIndex, message);
        this.queue.add(message);
        if (tailIndex == capacity){
            return false;
        } else {
            tailIndex++;
            return true;
        }
    }

    public KmqMessage poll() {
        //return queue.poll();
        if (headIndex == tailIndex){
            //no new message
            return null;
        }

        KmqMessage message = this.queue.get(headIndex);
        if (headIndex < capacity) {
            headIndex++;
        }
        return message;
    }

//    @SneakyThrows
//    public KmqMessage poll(long timeout) {
//        return queue.poll(timeout, TimeUnit.MILLISECONDS);
//    }

}
