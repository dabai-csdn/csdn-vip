package com.csdn.dabai;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleRabbitMQConsumer {
    private final static String EXCHANGE_DIRECT_NAME = "SIMPLE_DIRECT_EXCHANGE";
    private final static String QUEUE_DIRECT_NAME = "SIMPLE_DIRECT_QUEUE";

    private final static String EXCHANGE_TOPIC_NAME = "SIMPLE_TOPIC_EXCHANGE";
    private final static String QUEUE_TOPIC_NAME = "SIMPLE_TOPIC_QUEUE";

    private final static String EXCHANGE_FANOUT_NAME = "SIMPLE_FANOUT_EXCHANGE";
    private final static String QUEUE_FANOUT_NAME = "SIMPLE_FANOUT_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.199.201");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(EXCHANGE_DIRECT_NAME,"direct", false, false,null);
        channel.queueDeclare(QUEUE_DIRECT_NAME, false, false, false, null);
        channel.queueBind(QUEUE_DIRECT_NAME, EXCHANGE_DIRECT_NAME, "csdn.dabai");

        channel.exchangeDeclare(EXCHANGE_TOPIC_NAME,"topic", false, false,null);
        channel.queueDeclare(QUEUE_TOPIC_NAME, false, false, false, null);
        channel.queueBind(QUEUE_TOPIC_NAME, EXCHANGE_TOPIC_NAME, "*.dabai");

        channel.exchangeDeclare(EXCHANGE_FANOUT_NAME,"fanout", false, false,null);
        channel.queueDeclare(QUEUE_FANOUT_NAME, false, false, false, null);
        channel.queueBind(QUEUE_FANOUT_NAME, EXCHANGE_FANOUT_NAME, "");


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message : '" + msg + "'");
                System.out.println("consumerTag : " + consumerTag );
                System.out.println("deliveryTag : " + envelope.getDeliveryTag() );
            }
        };

        //消费消息
        channel.basicConsume(QUEUE_DIRECT_NAME, true, consumer);
        channel.basicConsume(QUEUE_TOPIC_NAME, true, consumer);
        channel.basicConsume(QUEUE_FANOUT_NAME, true, consumer);
    }
}
