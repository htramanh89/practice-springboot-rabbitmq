package com.practice.rabbitmq.service.impl;

import com.practice.rabbitmq.entity.Message;
import com.practice.rabbitmq.repository.MessageRepository;
import com.practice.rabbitmq.service.MessageService;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl (RabbitTemplate rabbitTemplate, TopicExchange senderExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = senderExchange;
    }

    public String sendGetRequest(String message) {
        String routingKey = "";
        if (message.contains("getAll")) {
            routingKey = "message.getAll";
        } else if(message.contains("getOne")) {
            routingKey = "message.getOne";
        } else if(message.contains("getByAuthor")) {
            routingKey = "message.getByAuthor";
       }
       message = message.substring(message.indexOf('?') + 1);
       return (String) rabbitTemplate.convertSendAndReceive(topicExchange.getName(), routingKey, message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getMessagesByAuthor(Long authorId) {
        return messageRepository.getMessagesByAuthorId(authorId);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message findMessageById(Long id) {
        return messageRepository.getOne(id);
    }
}
