package com.csdn.dabai.rabbitmqspringboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitCofig {
    //定义交换机（直连、主题、广播）
    @Bean("directExchange")
    public DirectExchange getDirectExchange() {
        return new DirectExchange("direct_exchange");
    }

    @Bean("topicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange("topic_exchange");
    }

    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange("fanout_exchange");
    }

    //定义队列（1个直连，1个主题，2个广播）
    @Bean("directQueue")
    public Queue getDirectQueue() {
        return new Queue("direct_queue");
    }
    @Bean("topicQueue")
    public Queue getTopicQueue() {
        return new Queue("topic_queue");
    }
    @Bean("fanoutQueue1")
    public Queue getFanoutQueue1() {
        return new Queue("fanout_queue_1");
    }
    @Bean("fanoutQueue2")
    public Queue getFanoutQueue2() {
        return new Queue("fanout_queue_2");
    }

    //定义3个绑定关系
    @Bean
    public Binding bindDirect(@Qualifier("directQueue") Queue queue, @Qualifier("directExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dabai.csdn.rabbitmq");
    }
    @Bean
    public Binding bindTopic(@Qualifier("topicQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.csdn.*");
    }
    @Bean
    public Binding bindFanout1(@Qualifier("fanoutQueue1") Queue queue,@Qualifier("fanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
    @Bean
    public Binding bindFanout2(@Qualifier("fanoutQueue2") Queue queue,@Qualifier("fanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

}
