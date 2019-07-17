package com.demo.jms;
import javax.jms.Queue;
import javax.jms.Topic;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.Date;
import java.util.UUID;

@Service("jmsProducer")
public class JmsProducer {

    @Autowired
    // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination="out.queue")
    public void consumerMessage(String text) {
        System.out.println("从out.queue队列收到的回复报文为:"+text);
    }


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send("abc123", message.toString());
    }


    void send(String topic, String key, String data) {
        kafkaTemplate.send(topic, key, data);
    }


}
