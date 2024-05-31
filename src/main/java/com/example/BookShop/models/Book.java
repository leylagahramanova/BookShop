package com.example.BookShop.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String author;
    private String category;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageFileName;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name; }
    public String getAuthor() { return author;}
    public void setAuthor(String author) { this.author = author; }
    public String getDescription() {return description; }
    public void setDescription(String description) {this.description = description; }
    public String getCategory() {return category; }
    public void setCategory(String category) {this.category = category; }
    public String getImageFileName() {return imageFileName;}
    public void setImageFileName(String imageFileName) {this.imageFileName =imageFileName;}
    public double getPrice() {return price;}
    public void setPrice(double price) { this.price = price;}
}