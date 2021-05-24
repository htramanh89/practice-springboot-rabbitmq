package com.practice.rabbitmq.service;

import com.practice.rabbitmq.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> getAllMessages();
    List<Message> getMessagesByAuthor(Long authorId);
    Message save(Message message);
    void delete(Long id);

    Optional<Message> findMessageById(Long id);
}
