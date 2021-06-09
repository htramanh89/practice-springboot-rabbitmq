package com.practice.rabbitmq.service;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorByUserName(String userName) {
        return authorRepository.findAuthorByUserName(userName);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }


    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author getOne(Long id) {
        return authorRepository.getOne(id);
    }
}
