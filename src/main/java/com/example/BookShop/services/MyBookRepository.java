package com.example.BookShop.services;

import com.example.BookShop.models.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBookRepository extends JpaRepository<MyBookList, Integer> {
    MyBookList findById(int id); // Update to match the actual property name
}