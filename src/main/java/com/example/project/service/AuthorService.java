package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.model.Author;
import com.example.project.repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // CREATE
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // READ ALL
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // READ BY ID
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    // DELETE
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
