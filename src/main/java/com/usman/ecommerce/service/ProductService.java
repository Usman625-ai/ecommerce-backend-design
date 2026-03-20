package com.usman.ecommerce.service;

import com.usman.ecommerce.model.Product;
import com.usman.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Product> search(String q) {
        return repo.findByNameContainingOrCategoryContaining(q, q);
    }

    public void save(Product p) {
        repo.save(p);
    }

    public Page<Product> getPaginated(int page) {
        return repo.findAll(PageRequest.of(page, 5));
    }
}