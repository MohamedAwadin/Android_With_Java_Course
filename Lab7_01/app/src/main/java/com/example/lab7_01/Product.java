package com.example.lab7_01;

public class Product {
    private String title;
    private double price;
    private String description;
    private float rating;
    private String thumbnail;

    public Product(String title, double price, String description, float rating, String thumbnail) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
