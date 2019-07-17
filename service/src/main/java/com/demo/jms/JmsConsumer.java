package com.demo.jms;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import com.demo.entity.model.Message;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class JmsConsumer {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }

    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")
    public String receiveQueue2(String text) {
        System.out.println("Consumer2收到的报文为:"+ text);
        return "return message"+text;
    }


    @KafkaListener(topics = "topic1")
    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("listenT1收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  " + cr.value());
    }

    @KafkaListener(topics = "topic2")
    public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("listenT2收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  " + cr.value());
    }
}
