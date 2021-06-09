 package com.practice.rabbitmq.controller.rest;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.repository.AuthorRepository;
import com.practice.rabbitmq.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorRestController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAll")
    List<Author> getAllAuthors () {
        return authorService.getAllAuthors();
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Author> saveNewAuthor (@RequestBody Author author) {
        Author _author = authorService.saveAuthor((new Author(author.getUserName(), author.getPassword(), author.isActive())));
        return new ResponseEntity<>(_author, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get")
    @ResponseBody
    Author getAuthorInfo(@RequestParam(name = "username") String userName) {
        return authorService.getAuthorByUserName(userName);
    }
}
