package com.usman.ecommerce.controller;

import com.usman.ecommerce.model.*;
import com.usman.ecommerce.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CartController {

    @Autowired
    private ProductRepository repo;

    private List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = getCart(session);

        double total = cart.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        model.addAttribute("cartCount", cart.size());

        return "cart";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {

        Product product = repo.findById(id).orElse(null);
        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                return "redirect:/products";
            }
        }

        cart.add(new CartItem(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                1
        ));

        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = getCart(session);

        cart.removeIf(item -> item.getProductId().equals(id));

        return "redirect:/cart";
    }
    @GetMapping("/increase/{id}")
    public String increase(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {
            if (item.getProductId().equals(id)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                }
            }
        }
        return "redirect:/cart";
    }
}