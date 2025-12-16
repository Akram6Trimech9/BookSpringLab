package com.example.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {}
