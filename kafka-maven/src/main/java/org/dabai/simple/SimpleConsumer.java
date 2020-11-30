package org.dabai.simple;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class SimpleConsumer {
    public static void main(String[] args) {
        Properties props= new Properties();
        props.put("bootstrap.servers","192.168.199.206:9092");
        props.put("group.id","gp-test-group");
        props.put("enable.auto.commit","true");
        props.put("auto.commit.interval.ms","1000");
        props.put("auto.offset.reset","earliest");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("mytopic"));

        try{
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String,String> record:records){
                    System.out.printf("offset = %d ,key =%s, value= %s, partition= %s%n" ,record.offset(),record.key(),record.value(),record.partition());
                }
            }
        }finally {
            consumer.close();
        }
    }
}
