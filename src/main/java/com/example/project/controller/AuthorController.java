package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Author;
import com.example.project.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

     @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

     @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
     @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

     @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
