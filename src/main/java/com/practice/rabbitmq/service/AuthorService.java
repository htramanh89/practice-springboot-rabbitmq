package com.practice.rabbitmq.service;


import com.practice.rabbitmq.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author saveAuthor (Author author);
    void deleteAuthor (Long id);

    Optional<Author> findAuthorById(Long id);
}
