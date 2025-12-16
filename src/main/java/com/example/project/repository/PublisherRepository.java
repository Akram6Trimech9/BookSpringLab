package com.example.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {}