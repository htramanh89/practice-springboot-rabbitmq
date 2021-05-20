package com.practice.rabbitmq.controller;

import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/author/view", method = RequestMethod.GET)
    public String viewAll(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "viewUser";
    }
}
