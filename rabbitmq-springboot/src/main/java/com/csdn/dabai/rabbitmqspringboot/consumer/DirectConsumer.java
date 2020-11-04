package com.csdn.dabai.rabbitmqspringboot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct_queue")
public class DirectConsumer {
    @RabbitHandler
    public void process(String msg) {
        System.out.println(" direct queue received msg : " + msg);
    }
}
