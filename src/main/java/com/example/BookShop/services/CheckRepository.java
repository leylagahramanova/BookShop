package com.example.BookShop.services;

import com.example.BookShop.models.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<Checkout, Integer> {
    // Define custom methods if needed
}
