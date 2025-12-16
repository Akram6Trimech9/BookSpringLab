package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Tag;
import com.example.project.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

     @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

     @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

     @GetMapping("/{id}")
    public Tag getTag(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

     @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}
