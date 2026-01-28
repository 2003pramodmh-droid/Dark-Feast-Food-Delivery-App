package com.tap.model;

/**
 * Restaurant Model - Represents a restaurant
 */
public class Restaurant {
    private int id;
    private String name;
    private double rating;
    private String description;
    private String imageUrl;

    // Default Constructor
    public Restaurant() {}

    // Constructor
    public Restaurant(int id, String name, double rating, String description) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.description = description;
    }

    // Full Constructor
    public Restaurant(int id, String name, double rating, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Override
    public String toString() {
        return "Restaurant{id=" + id + ", name='" + name + "', rating=" + rating + "}";
    }
}
