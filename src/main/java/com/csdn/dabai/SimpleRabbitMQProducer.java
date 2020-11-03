package com.csdn.dabai;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleRabbitMQProducer {
    private final static String EXCHANGE_DIRECT_NAME = "SIMPLE_DIRECT_EXCHANGE";

    private final static String EXCHANGE_TOPIC_NAME = "SIMPLE_TOPIC_EXCHANGE";

    private final static String EXCHANGE_FANOUT_NAME = "SIMPLE_FANOUT_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.199.201");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String msg1 = "csdn,dabai1!";
        String msg2 = "csdn,dabai2!";
        String msg3 = "csdn,dabai3!";
        //直连
        channel.basicPublish(EXCHANGE_DIRECT_NAME, "csdn.dabai", null, msg1.getBytes());
        //主题
        channel.basicPublish(EXCHANGE_TOPIC_NAME, "*.dabai", null, msg2.getBytes());
        //广播
        channel.basicPublish(EXCHANGE_FANOUT_NAME, "", null, msg3.getBytes());

        channel.close();
        connection.close();
    }
}
