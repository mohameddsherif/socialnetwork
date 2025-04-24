package com.socialnetwork.jms;

import javax.jms.*;
import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class MessageProducer {

    @Resource(mappedName = "java:/jms/queue/NotificationQueue")
    private Queue queue;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    public void sendNotification(String message) {
        try (Connection connection = connectionFactory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            javax.jms.MessageProducer producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
