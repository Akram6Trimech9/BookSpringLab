package com.example.project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.project.model.Book;
import com.example.project.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook(Book book) {
        if (book.getId() == null) {
            bookRepository.save(book);
        } else {
            Book existingBook = bookRepository.findById(book.getId()).orElse(null);
            if (existingBook == null) {
                book.setId(null);
                bookRepository.save(book);
            } else {
                existingBook.setTitle(book.getTitle());
                existingBook.setAuthor(book.getAuthor());
                existingBook.setIsbn(book.getIsbn());
                existingBook.setPrice(book.getPrice());
                existingBook.setQuantity(book.getQuantity());
                existingBook.setCategory(book.getCategory());
                bookRepository.save(existingBook);
            }
        }
    }

    public int inventory(String category) {
        return bookRepository.countByCategory(category);
    }

    public void update() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            book.setPrice(book.getPrice() * 1.10);
        }
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
                .filter(book -> book.getPrice() >= min && book.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public int getTotalBooks() {
        return (int) bookRepository.count();
    }

    public Set<String> getUniqueAuthors() {
        return bookRepository.findAll().stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());
    }
}
