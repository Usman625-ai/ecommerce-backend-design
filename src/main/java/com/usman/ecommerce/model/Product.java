package com.usman.ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String name;
    private double price;
    private String category;
    private String description;
    private int stock;

    public Product() {}

    public Product(Long id, String name, double price, String category,
                   String description, int stock, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    // ✅ GETTERS (VERY IMPORTANT)
    public Long getId() { return id; }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public String getCategory() { return category; }

    public String getDescription() { return description; }

    public int getStock() { return stock; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    // ✅ SETTERS
    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPrice(double price) { this.price = price; }

    public void setCategory(String category) { this.category = category; }

    public void setDescription(String description) { this.description = description; }

    public void setStock(int stock) { this.stock = stock; }
}