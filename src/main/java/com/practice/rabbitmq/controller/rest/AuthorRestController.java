 package com.practice.rabbitmq.controller.rest;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorRestController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAllAuthors")
    List<Author> getAllAuthors () {
        return authorService.getAllAuthors();
    }
}
