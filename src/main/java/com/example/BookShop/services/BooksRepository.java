package com.example.BookShop.services;

import com.example.BookShop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByCategory(String category);

}
