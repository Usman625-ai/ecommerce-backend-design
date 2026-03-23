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
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        List<Product> cart = getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("total", cart.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum());
        model.addAttribute("cartCount", cart.size());
        return "cart";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        List<Product> cart = getCart(session);
        Product p = productService.getById(id);

        boolean found = false;
        for (Product item : cart) {
            if (item.getId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            Product copy = new Product(p);
            copy.setQuantity(1);
            cart.add(copy);
        }
        session.setAttribute("cart", cart);
        return "redirect:/";
    }

    @GetMapping("/increase/{id}")
    public String increase(@PathVariable Long id, HttpSession session) {
        List<Product> cart = getCart(session);
        cart.forEach(p -> {
            if (p.getId().equals(id)) p.setQuantity(p.getQuantity() + 1);
        });
        return "redirect:/cart";
    }

    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable Long id, HttpSession session) {
        List<Product> cart = getCart(session);
        cart.removeIf(p -> {
            if (p.getId().equals(id)) {
                p.setQuantity(p.getQuantity() - 1);
                return p.getQuantity() <= 0;
            }
            return false;
        });
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session) {
        List<Product> cart = getCart(session);
        cart.removeIf(p -> p.getId().equals(id));
        return "redirect:/cart";
    }

    private List<Product> getCart(HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}