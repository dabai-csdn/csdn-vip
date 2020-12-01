package com.dabai.kafkaspringboot;

import com.dabai.kafkaspringboot.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class KafkaTest {
    @Autowired
    KafkaProducer producer;

    @Test
    void testSendMsg() {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("----"+time +"，已经发出----");
        producer.send("大白测试kafka, " +time);
    }

}
