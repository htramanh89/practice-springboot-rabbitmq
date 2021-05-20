 package com.practice.rabbitmq.controller.rest;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorRestController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/getAll")
    List<Author> getAllAuthors () {
        return authorRepository.findAll();
    }

    @PostMapping(path="/save")
    public ResponseEntity<Author> saveNewAuthor (@RequestBody Author author) {
            System.out.println(author.getUserName());
            Author _author = authorRepository
                    .save(new Author(author.getUserName(), author.getPassword(), author.isActive()));
            return new ResponseEntity<>(_author, HttpStatus.CREATED);
    }
}
