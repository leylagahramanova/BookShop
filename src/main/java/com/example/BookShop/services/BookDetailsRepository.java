package com.example.BookShop.services;

import com.example.BookShop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailsRepository extends JpaRepository<Book, Integer> {
    // Define custom methods if needed
}
