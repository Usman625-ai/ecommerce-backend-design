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

            repo.save(new Product(null, "iPhone 14", 999, "Mobile", "Apple phone", 10,
                    "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9"));

            repo.save(new Product(null, "Samsung S23", 850, "Mobile", "Samsung flagship", 15,
                    "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf"));

            repo.save(new Product(null, "MacBook Pro", 2000, "Laptop", "Apple laptop", 5,
                    "https://images.unsplash.com/photo-1517336714731-489689fd1ca8"));

            repo.save(new Product(null, "Dell XPS", 1500, "Laptop", "Dell premium laptop", 7,
                    "https://images.unsplash.com/photo-1587202372775-e229f172b9d7"));

            repo.save(new Product(null, "Headphones", 200, "Accessories", "Wireless headphones", 20,
                    "https://images.unsplash.com/photo-1518444065439-e933c06ce9cd"));

            repo.save(new Product(null, "Smart Watch", 300, "Accessories", "Fitness smartwatch", 12,
                    "https://images.unsplash.com/photo-1519744346363-d1c0d4c5c7c1"));

            repo.save(new Product(null, "Gaming Mouse", 80, "Accessories", "RGB gaming mouse", 25,
                    "https://images.unsplash.com/photo-1587202372634-32705e3bf49c"));

            repo.save(new Product(null, "Mechanical Keyboard", 150, "Accessories", "RGB keyboard", 18,
                    "https://images.unsplash.com/photo-1517331156700-3c241d2b4d83"));
        };
    }
}