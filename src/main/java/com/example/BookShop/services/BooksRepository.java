package com.example.BookShop.services;

import com.example.BookShop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByCategory(String category);

    @Query("SELECT book FROM Book book WHERE " +
            "(:name IS NULL OR book.name LIKE %:name%) AND " +
            "(:author IS NULL OR book.author LIKE %:author%) AND " +
            "(:category IS NULL OR book.category = :category)")
    List<Book> findByFilters(@Param("name") String name,
                             @Param("author") String author,
                             @Param("category") String category);
}
