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
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByUserName(String userName) {
        return authorRepository.findAuthorByUserName(userName);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }
}
