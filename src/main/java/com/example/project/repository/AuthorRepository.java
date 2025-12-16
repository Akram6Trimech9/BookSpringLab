package com.example.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.Author;
 
public interface AuthorRepository extends JpaRepository<Author, Long> {}
