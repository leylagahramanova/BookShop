package com.example.BookShop.services;

import com.example.BookShop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Book, Integer> {
}
