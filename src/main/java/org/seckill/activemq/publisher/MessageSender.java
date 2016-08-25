package org.seckill.activemq.publisher;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * Created by LJ on 2016/8/25.
 */
public class MessageSender {
    private final JmsTemplate jmsTemplate;
    private final Destination destination;

    public MessageSender(final JmsTemplate jmsTemplate, final Destination destination) {
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
    }

    public void send(final String text) {
        try {
            jmsTemplate.setDefaultDestination(destination);
            jmsTemplate.convertAndSend(text);
            System.out.println("P2P发送消息 : " + text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
