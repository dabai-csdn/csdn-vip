package com.csdn.dabai.rabbitmqspringboot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout_queue_1")
public class FanoutConsumer1 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println(" fanout queue 1 received msg : " + msg);
    }
}
