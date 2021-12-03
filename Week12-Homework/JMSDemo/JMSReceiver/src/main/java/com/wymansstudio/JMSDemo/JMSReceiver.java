package com.wymansstudio.JMSDemo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JMSReceiver {
    public static void main(String[] args) throws JMSException, IOException {

        //default using localhost:61616
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("Test01");
        MessageConsumer messageConsumer = session.createConsumer(queue);

        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("Received: " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
