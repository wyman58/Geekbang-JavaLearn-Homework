package com.wymansstudio.JMSDemo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JMSMessagePublisher {
    public static void main(String[] args) throws JMSException, IOException {

        //default using localhost:61616
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination topic = session.createTopic("Topic01");
        MessageProducer messageProducer = session.createProducer(topic);

        for(int i = 0; i < 5; i++){
            messageProducer.send(session.createTextMessage("message: " + i));
        }
        messageProducer.close();
        session.close();
        connection.close();
    }
}
