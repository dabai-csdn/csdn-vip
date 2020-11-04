package com.csdn.dabai.rabbitmqspringboot.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitProvider {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(){
        // 发送4条消息
        amqpTemplate.convertAndSend("direct_exchange","dabai.csdn.rabbitmq","-------- a direct msg");

        amqpTemplate.convertAndSend("topic_exchange","dabai.csdn.message1","-------- a topic msg : dabai.csdn.message1");
        amqpTemplate.convertAndSend("topic_exchange","rabbit.csdn.message2","-------- a topic msg : rabbit.csdn.message2");

        amqpTemplate.convertAndSend("fanout_exchange","","-------- a fanout msg");

    }

}
