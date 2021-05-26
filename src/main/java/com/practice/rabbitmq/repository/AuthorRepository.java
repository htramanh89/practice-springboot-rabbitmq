package com.practice.rabbitmq.repository;

import com.practice.rabbitmq.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository <Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.userName = :name")
    Author findAuthorByUserName(@Param("name") String userName);
}
