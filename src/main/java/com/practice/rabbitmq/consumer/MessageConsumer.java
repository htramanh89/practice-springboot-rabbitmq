package com.practice.rabbitmq.consumer;

import com.practice.rabbitmq.repository.AuthorRepository;
import com.practice.rabbitmq.repository.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {
    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues="messageServiceQueue")
    public void receive(String message) {
        System.out.println("Received message: " + message);
        System.out.println("Size: " + messageRepository.findAll().size());
    }
}
