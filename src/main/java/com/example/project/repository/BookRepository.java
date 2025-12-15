package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);
    boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);
    int countByCategory(String category);
    Book findByIsbn(String isbn);
}
