package com.practice.rabbitmq.service;


import com.practice.rabbitmq.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAllAuthors();
    void saveAuthor (Author author);
    void deleteAuthor (String username);

    Optional<Author> findAuthorByUserName(String userName);
}
