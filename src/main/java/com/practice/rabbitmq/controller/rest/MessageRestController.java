package com.practice.rabbitmq.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.rabbitmq.entity.Message;
import com.practice.rabbitmq.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageRestController {
    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/getAll")
    public List<Message> getAllMessages() throws JsonProcessingException {
        String result = messageService.sendGetRequest("getAll");
        List<Message> messages = new ObjectMapper().readValue(result, new TypeReference<List<Message>>() {});
        return messages;
    }

    @GetMapping("/get")
    public Message getMessageById(@RequestParam(name = "id") Long id) throws JsonProcessingException {
        String result = messageService.sendGetRequest("getOne?id=" + id.toString());
        return new ObjectMapper().readValue(result, Message.class);
    }

    @GetMapping("/getByAuthor")
    public List<Message> getMessagesByAuthor(@RequestParam(name = "authorId") Long authorId) throws JsonProcessingException {
        String result = messageService.sendGetRequest("getByAuthor?authorId=" + authorId.toString());
        List<Message> messages = new ObjectMapper().readValue(result, new TypeReference<List<Message>>() {});
        return messages;
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody String message) throws JsonProcessingException {
        String result = messageService.sendPostRequest(message);
        return new ResponseEntity<> (new ObjectMapper().readValue(result, Message.class), HttpStatus.CREATED);
    }
}
