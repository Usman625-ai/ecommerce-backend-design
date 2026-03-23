package com.usman.ecommerce;

import com.usman.ecommerce.model.Product;
import com.usman.ecommerce.repository.ProductRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository repo) {
        return args -> {

            repo.save(new Product(null, "iPhone 14", 455000, "Mobile", "Apple phone", 10,
                    "Iphone14.jpg"));

            repo.save(new Product(null, "GooglePixel", 125000, "Mobile", "GooglePixel flagship", 15,
                    "GooglePixel.jpg"));

            repo.save(new Product(null, "MacBook Pro", 198999, "Laptop", "Apple laptop", 5,
                    "MacBook.jpg"));

            repo.save(new Product(null, "Dell XPS", 150000, "Laptop", "Dell premium laptop", 7,
                    "Laptop.jpg"));

            repo.save(new Product(null, "Headphones", 35000, "Accessories", "Wireless headphones", 20,
                    "Headphones.jpg"));

            repo.save(new Product(null, "Smart Watch", 24000, "Accessories", "Fitness smartwatch", 12,
                    "SmartPhone.jpg"));

            repo.save(new Product(null, "Gaming Mouse", 8000, "Accessories", "RGB gaming mouse", 25,
                    "Mouse.jpg"));

            repo.save(new Product(null, "Mechanical Keyboard", 14000, "Accessories", "RGB keyboard", 18,
                    "KeyBoard.jpg"));
        };
    }
}