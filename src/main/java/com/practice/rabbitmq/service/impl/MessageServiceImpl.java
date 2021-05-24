package com.practice.rabbitmq.service.impl;

import com.practice.rabbitmq.entity.Message;
import com.practice.rabbitmq.repository.MessageRepository;
import com.practice.rabbitmq.service.MessageService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;
    @Autowired
    private MessageRepository messageRepository;


    @Autowired
    public MessageServiceImpl (RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    public void sendMessage() {
        String routingKey = "send.message";
        String message = "Message was sent.";
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
    }


    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getMessagesByAuthor(Long authorId) {
        return null;
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
    public Optional<Message> findMessageById(Long id) {
        return messageRepository.findById(id);
    }
}
