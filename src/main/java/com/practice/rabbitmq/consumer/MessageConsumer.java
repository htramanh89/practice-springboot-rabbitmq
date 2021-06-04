package com.practice.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.rabbitmq.entity.Message;
import com.practice.rabbitmq.repository.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {
    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues="messageGetServiceQueue")
    public String receiveGetRequest(String message) {
        System.out.println("Received message: " + message);
        if(message.contains("id")) {
            Long id = Long.parseLong(message.substring(message.indexOf("=") + 1));
            return messageRepository.getOne(id).toString();
        } else if(message.contains("getAll")) {
            return messageRepository.findAll().toString();
        } else if(message.contains("authorId")) {
            Long authorId = Long.parseLong(message.substring(message.indexOf("=") + 1));
            return messageRepository.getMessagesByAuthorId(authorId).toString();
        }
        return "Received message: " + message;
    }

    @RabbitListener(queues="messagePostServiceQueue")
    public String receivePostRequest(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();//.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return messageRepository.save(mapper.readValue(message, Message.class)).toString();
    }

}
