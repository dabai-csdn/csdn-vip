package com.csdn.dabai.rabbitmqspringboot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic_queue")
public class TopicConsumer {
    @RabbitHandler
    public void process(String msg) {
        System.out.println(" topic queue received msg : " + msg);
    }
}
