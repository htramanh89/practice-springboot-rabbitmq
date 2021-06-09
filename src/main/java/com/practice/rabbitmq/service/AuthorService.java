package com.practice.rabbitmq.service;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Cacheable(value = "authors")
    public List<Author> getAllAuthors() {
        simulateSlowService();
        return authorRepository.findAll();
    }

    @Cacheable(value="author", key = "#userName")
    public Author getAuthorByUserName(String userName) {
        simulateSlowService();
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
        simulateSlowService();
        return authorRepository.getOne(id);
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
            System.out.println("Going to sleep for 3 Secs.. to simulate backend call.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
