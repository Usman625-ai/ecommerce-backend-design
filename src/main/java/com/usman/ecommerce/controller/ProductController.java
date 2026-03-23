package com.usman.ecommerce.controller;

import com.usman.ecommerce.model.Product;
import com.usman.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // Home Page
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("products", productService.getTop10());
        model.addAttribute("cartCount", getCartCount(session));
        return "home";
    }

    // Products Page
    @GetMapping("/products")
    public String products(Model model, @RequestParam(required = false) String query, HttpSession session) {
        List<Product> products;
        if (query != null && !query.isEmpty()) {
            products = productService.search(query);
        } else {
            products = productService.getAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("cartCount", getCartCount(session));
        return "products";
    }

    // Product Details
    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable Long id, Model model) {

        Product product = productService.getById(id);

        model.addAttribute("product", product);

        return "product-details"; // MUST match file name
    }

    // Helper method to get cart count
    private int getCartCount(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        return cart == null ? 0 : cart.size();
    }
}