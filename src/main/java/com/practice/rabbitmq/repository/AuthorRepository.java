package com.practice.rabbitmq.repository;

import com.practice.rabbitmq.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, Long> {
}
