package com.usman.ecommerce.model;

public class CartItem {

    private Long productId;
    private String name;
    private double price;
    private String imageUrl;
    private int quantity;

    public CartItem(Long productId, String name, double price, String imageUrl, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public Long getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}