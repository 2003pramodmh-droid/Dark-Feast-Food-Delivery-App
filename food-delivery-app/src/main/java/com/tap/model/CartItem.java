package com.tap.model;

/**
 * CartItem Model - Represents an item in the shopping cart
 * Used for session management
 */
public class CartItem {
    private int itemId;
    private String name;
    private double price;
    private int quantity;
    private int restaurantId;
    private String restaurantName;

    // Default Constructor
    public CartItem() {
    }

    // Constructor
    public CartItem(int itemId, String name, double price, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Full Constructor
    public CartItem(int itemId, String name, double price, int quantity, int restaurantId, String restaurantName) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * Calculate total price for this cart item
     * 
     * @return price * quantity
     */
    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{name='" + name + "', quantity=" + quantity + ", subtotal=" + getSubtotal() + "}";
    }
}
