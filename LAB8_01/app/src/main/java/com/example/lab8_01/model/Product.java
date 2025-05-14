package com.example.lab8_01.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName =  "favorite_products")
public class Product {
    @PrimaryKey
    private int id ;
    private String title;
    private double price;
    private String description;
    private float rating;
    private String thumbnail;


    public Product(int id, String title, double price, String description, float rating, String thumbnail) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.thumbnail = thumbnail;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
