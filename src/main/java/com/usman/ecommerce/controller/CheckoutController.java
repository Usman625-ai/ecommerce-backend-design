package com.usman.ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {

        List<?> cart = (List<?>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("cart", cart);

        return "checkout";
    }

    @GetMapping("/place-order")
    public String placeOrder(HttpSession session) {
        session.removeAttribute("cart"); // clear cart
        return "success";
    }
}
