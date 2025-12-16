package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.model.Tag;
import com.example.project.repository.TagRepository;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // CREATE
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // READ ALL
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // READ BY ID
    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    // DELETE
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
