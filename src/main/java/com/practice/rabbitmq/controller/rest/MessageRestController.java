package com.practice.rabbitmq.controller.rest;

import com.practice.rabbitmq.entity.Message;
import com.practice.rabbitmq.producer.MessageProducerConfiguration;
import com.practice.rabbitmq.repository.MessageRepository;
import com.practice.rabbitmq.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageRestController {
    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/getAll")
    public void sendMessageGetAll() {
        messageService.sendMessage("/getAll");
    }

}
