package com.practice.rabbitmq.consumer;

import com.practice.rabbitmq.repository.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {
    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues="messageServiceQueue")
    public String receive(String message) {
        System.out.println("Received message: " + message);
        if(message.contains("id")) {
            Long id = Long.parseLong(message.substring(message.indexOf("=") + 1));
            return messageRepository.getMessagesByAuthorId(1L).get(0).toString();
        }
        else if(message.contains("getAll")) {
            return messageRepository.findAll().toString();
        } else if(message.contains("authorId")) {
            Long authorId = Long.parseLong(message.substring(message.indexOf("=") + 1));
            return messageRepository.getMessagesByAuthorId(authorId).toString();
        }

        return "Received message: " + message;
    }
}
