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

     public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

     public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

     public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

     public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
