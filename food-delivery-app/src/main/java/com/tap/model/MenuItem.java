package com.tap.model;

/**
 * MenuItem Model - Represents a food item in a restaurant's menu
 */
public class MenuItem {
    private int id;
    private int restaurantId;
    private String name;
    private double price;
    private double rating;
    private String description;
    private String imageUrl;

    // Default Constructor
    public MenuItem() {
    }

    // Constructor
    public MenuItem(int id, String name, double price, double rating, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    // Full Constructor
    public MenuItem(int id, int restaurantId, String name, double price, double rating, String description) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "MenuItem{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}
