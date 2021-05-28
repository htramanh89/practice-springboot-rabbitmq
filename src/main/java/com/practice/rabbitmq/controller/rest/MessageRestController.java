package com.practice.rabbitmq.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.rabbitmq.entity.Message;
import com.practice.rabbitmq.entity.MessageDeserializer;
import com.practice.rabbitmq.producer.MessageProducerConfiguration;
import com.practice.rabbitmq.repository.MessageRepository;
import com.practice.rabbitmq.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageRestController {
    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/getAll")
    public String getAllMessages() {
        return messageService.sendGetRequest("getAll");
    }

    @GetMapping("/get")
    public Message getMessageById(@RequestParam(name = "id") Long id) throws JsonProcessingException {
        String result = messageService.sendGetRequest("getOne?id=" + id.toString());
        return new ObjectMapper().readValue(result, Message.class);
    }

    @GetMapping("/getByAuthor")
    public String getMessagesByAuthor(@RequestParam(name = "authorId") Long authorId) {
        return messageService.sendGetRequest("getByAuthor?authorId=" + authorId.toString());
    }

}
