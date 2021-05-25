package com.practice.rabbitmq.controller.rest;

import com.practice.rabbitmq.entity.Message;
import com.practice.rabbitmq.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageRestController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/getAll")
    List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

}
