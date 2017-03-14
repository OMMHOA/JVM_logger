package com.receiver.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.receiver.service.MessageService;

import java.io.UnsupportedEncodingException;

@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @Autowired
    private MessageService messageService;


    @RabbitHandler
    public void receive(byte[] payload) throws UnsupportedEncodingException {
        String message = new String(payload, "UTF-8");
        System.out.println("Receiver got: " + message);
        messageService.processMessage(message);
        System.out.println("Receiver ready again!");
    }

}
