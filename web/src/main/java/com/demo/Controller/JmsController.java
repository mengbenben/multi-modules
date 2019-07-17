package com.demo.Controller;

import com.demo.entity.common.R;
import com.demo.entity.model.User;
import com.demo.jms.JmsProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import java.util.List;

@RestController
@RequestMapping("/jms")
public class JmsController {
    @Autowired
    private JmsProducer jmsProducer;

    @GetMapping("/test")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void jmsTest() {

        Destination destination = new ActiveMQQueue("mytest.queue");

        for(int i=0; i<10; i++){
            jmsProducer.sendMessage(destination, "myname is *******");
        }
    }

}
