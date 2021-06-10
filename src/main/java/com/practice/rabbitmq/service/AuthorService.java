package com.practice.rabbitmq.service;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Cacheable(value="author", key = "#userName")
    public Author getAuthorByUserName(String userName) {
        simulateSlowService();
        return authorRepository.findAuthorByUserName(userName);
    }

    @Caching(put = {
            @CachePut(value = "author" , key = "#author.id"),
            @CachePut(value = "author" , key = "#author.userName")
    })
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @CacheEvict(value = "author", key = "#id")
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Cacheable(value="author", key = "#id")
    public Author getOne(Long id) {
        simulateSlowService();
        return authorRepository.findById(id).get();
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
