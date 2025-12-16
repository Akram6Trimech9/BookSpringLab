package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Publisher;
import com.example.project.service.PublisherService;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

     @PostMapping
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.createPublisher(publisher);
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

     @GetMapping("/{id}")
    public Publisher getPublisher(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

     @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }
}
