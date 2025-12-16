package com.example.project.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.CreateBookRequest;
import com.example.project.model.Book;
import com.example.project.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/saveBook")
    public void addBook(@Valid @RequestBody CreateBookRequest request) {
        bookService.saveBook(request);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/inventory")
    public int inventory(@RequestParam String category) {
        return bookService.inventory(category);
    }

    @PutMapping("/updateBook")
    public void updatePrice() {
        bookService.update();
    }

    @DeleteMapping("/DeleteBook/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }

    @GetMapping("/price/{min}/{max}")
    public List<Book> byPrice(@PathVariable double min, @PathVariable double max) {
        return bookService.getBooksByPriceRange(min, max);
    }

    @GetMapping("/total")
    public int total() {
        return bookService.getTotalBooks();
    }

    @GetMapping("/authors")
    public Set<String> authors() {
        return bookService.getUniqueAuthors();
    }
}
