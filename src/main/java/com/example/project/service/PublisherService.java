package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.model.Publisher;
import com.example.project.repository.PublisherRepository;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    // CREATE
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    // READ ALL
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // READ BY ID
    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    // DELETE
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
