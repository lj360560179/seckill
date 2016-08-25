package org.seckill.activemq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by LJ on 2016/8/25.
 */
public class MessageReceiverTopic1 implements MessageListener {
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                System.out.println("Topic1接收到消息: " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
