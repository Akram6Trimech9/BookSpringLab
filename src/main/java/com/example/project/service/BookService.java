package com.example.project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.project.dto.CreateBookRequest;
import com.example.project.model.Author;
import com.example.project.model.Book;
import com.example.project.model.Publisher;
import com.example.project.model.Tag;
import com.example.project.repository.AuthorRepository;
import com.example.project.repository.BookRepository;
import com.example.project.repository.PublisherRepository;
import com.example.project.repository.TagRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final TagRepository tagRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       PublisherRepository publisherRepository,
                       TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.tagRepository = tagRepository;
    }

    public void saveBook(CreateBookRequest request) {

        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new IllegalArgumentException("ISBN already exists");
        }

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        book.setAuthor(author);

        if (request.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(request.getPublisherId())
                    .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
            book.setPublisher(publisher);
        }

        if (request.getTagIds() != null) {
            Set<Tag> tags = request.getTagIds().stream()
                    .map(id -> tagRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Tag not found: " + id)))
                    .collect(Collectors.toSet());
            book.setTags(tags);
        }

        bookRepository.save(book);
    }

    public int inventory(String category) {
        return bookRepository.countByCategory(category);
    }

    public void update() {
        List<Book> books = bookRepository.findAll();
        books.forEach(b -> b.setPrice(b.getPrice() * 1.10));
        bookRepository.saveAll(books);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByPriceRange(double min, double max) {
        return bookRepository.findAll().stream()
                .filter(b -> b.getPrice() >= min && b.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public int getTotalBooks() {
        return (int) bookRepository.count();
    }

    public Set<String> getUniqueAuthors() {
        return bookRepository.findAll().stream()
                .map(b -> b.getAuthor().getName())
                .collect(Collectors.toSet());
    }
}
