package com.practice.rabbitmq.service;

import com.practice.rabbitmq.repository.MessageRepository;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate, TopicExchange senderExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = senderExchange;
    }

    public String sendGetRequest(String message) {
        String routingKey = "";
        if (message.contains("getAll")) {
            routingKey = "message.get.All";
        } else if(message.contains("getOne")) {
            routingKey = "message.get.One";
        } else if(message.contains("getByAuthor")) {
            routingKey = "message.get.ByAuthor";
        } else if(message.contains("getByAuthor")) {
            routingKey = "message.get.ByAuthor";
        }
       message = message.substring(message.indexOf('?') + 1);
       return (String) rabbitTemplate.convertSendAndReceive(topicExchange.getName(), routingKey, message);
    }

    public String sendPostRequest (String message) {
        return (String) rabbitTemplate.convertSendAndReceive(topicExchange.getName(), "message.save", message);
    }
}
