package com.practice.rabbitmq.service.impl;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.repository.AuthorRepository;
import com.practice.rabbitmq.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String username) {
        authorRepository.deleteById(username);
    }

    @Override
    public Optional<Author> findAuthorByUserName(String userName) {
        return authorRepository.findById(userName);
    }
}
