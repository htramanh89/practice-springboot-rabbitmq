package com.practice.rabbitmq.repository;

import com.practice.rabbitmq.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.authorId = :authorId")
    List<Message> getMessagesByAuthorId(@Param("authorId") Long authorId);
}
