package com.example.project.model;

import com.example.project.validation.ValidISBN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "ISBN cannot be blank")
    @ValidISBN
    @Column(nullable = false, unique = true)
    private String isbn;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Column(nullable = false)
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private int quantity;

    private String category;

    @Version
    private Integer version;

    public Book() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
