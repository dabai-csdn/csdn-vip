package com.csdn.dabai.rabbitmqspringboot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout_queue_2")
public class FanoutConsumer2 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println(" fanout queue 2 received msg : " + msg);
    }
}
