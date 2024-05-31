package com.example.BookShop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public class MyBookList {

    @Id
    private int id;
    private String name;
    private String author;
    private double price;
    private String imageFileName;

    // No-arg constructor
    public MyBookList() {
    }

    // Constructor with parameters
    public MyBookList(int id, String name, String author, double price, String imageFileName) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.imageFileName = imageFileName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
