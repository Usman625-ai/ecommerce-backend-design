package com.usman.ecommerce.controller;

import com.usman.ecommerce.model.Product;
import com.usman.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        model.addAttribute("products", service.getAll());

        // ✅ ADD THIS
        model.addAttribute("cartCount",
                session.getAttribute("cart") == null ? 0 :
                        ((java.util.List<?>) session.getAttribute("cart")).size()
        );

        return "home";
    }

    @GetMapping("/products")
    public String products(@RequestParam(defaultValue = "0") int page,
                           Model model,
                           HttpSession session) {

        Page<Product> productPage = service.getPaginated(page);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        // ✅ ADD THIS LINE (IMPORTANT)
        model.addAttribute("cartCount",
                session.getAttribute("cart") == null ? 0 :
                        ((java.util.List<?>) session.getAttribute("cart")).size()
        );

        return "products";
    }

    @GetMapping("/products/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.getById(id));
        return "product-details";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("products", service.search(query));
        return "products";
    }

    @GetMapping("/add")
    public String addForm(HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "add-product";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products";
    }
}