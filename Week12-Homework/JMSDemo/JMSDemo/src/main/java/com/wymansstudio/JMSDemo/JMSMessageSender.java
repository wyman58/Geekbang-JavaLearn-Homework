package com.wymansstudio.JMSDemo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Text;

import javax.jms.*;
import java.io.IOException;

public class JMSMessageSender {
    public static void main(String[] args) throws JMSException, IOException {

        //default using localhost:61616
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("Test01");
        MessageProducer messageProducer = session.createProducer(queue);

        for(int i = 0; i < 5; i++){
            messageProducer.send(session.createTextMessage("message: " + i));
        }
        messageProducer.close();
        session.close();
        connection.close();
    }
}
