package com.practice.rabbitmq.repository;

import com.practice.rabbitmq.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
