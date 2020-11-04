package com.csdn.dabai.rabbitmqspringboot;

import com.csdn.dabai.rabbitmqspringboot.provider.RabbitProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqSpringbootApplicationTests {

    @Autowired
    RabbitProvider provider;

    @Test
    void contextLoads() {
        provider.send();
    }

}
