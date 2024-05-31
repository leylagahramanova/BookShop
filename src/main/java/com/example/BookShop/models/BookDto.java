package com.example.BookShop.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class BookDto {
        @NotEmpty(message = "The name is required")
        private String name;
        @NotEmpty(message = "The author is required")
        private String author;
        @NotEmpty(message = "The category is required")
        private String category;
        @Min(0)
        private double price;
        @Size(min = 10, message = "The description should be at least 10 characters")
        @Size(max = 2000, message = "The description cannot exceed 2000 characters")
        private String description;
        private MultipartFile imageFile;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public MultipartFile getImageFile() { return imageFile; }
        public void setImageFile(MultipartFile imageFile) { this.imageFile = imageFile; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
}
