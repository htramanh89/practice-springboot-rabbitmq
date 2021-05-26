package com.practice.rabbitmq.controller.rest;

import com.practice.rabbitmq.entity.Message;
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
    public void getAllMessages() {
        messageService.sendGetAllMessages("/message/getAll");
    }

    @GetMapping("/getByAuthor")
    @ResponseBody
    public void getMessagesByAuthor(@RequestParam(name = "id") Long authorId) {
        messageService.sendGetMessagesByAuthor("/message/getByAuthor?id=" + authorId.toString());
        //return messageService.getMessagesByAuthor(authorId);
    }

}
