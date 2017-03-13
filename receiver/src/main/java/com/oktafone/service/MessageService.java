package com.oktafone.service;

import com.oktafone.dal.MessageDao;
import com.oktafone.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Async
    public void processMessage(String message){
        Message messageEntity = new Message();
        messageEntity.setMessage(message);
        messageDao.save(messageEntity);
        System.out.println("Message persisted: " + message);
    }

}
