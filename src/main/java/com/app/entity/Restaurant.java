package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "rating")
    private float rating;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "delivery_time")
    private int deliveryTime;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "popular")
    private boolean popular;

    public Restaurant() {
    }

    public Restaurant(String name, String address, float rating, String cuisineType, boolean isActive, int deliveryTime,
            String imagePath, boolean popular) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.isActive = isActive;
        this.deliveryTime = deliveryTime;
        this.imagePath = imagePath;
    }

    public Restaurant(int restaurantId, String name, String address, float rating, String cuisineType, boolean isActive,
            int deliveryTime, String imagePath, boolean popular) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.isActive = isActive;
        this.deliveryTime = deliveryTime;
        this.imagePath = imagePath;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", rating="
                + rating + ", cuisineType=" + cuisineType + ", isActive=" + isActive + ", deliveryTime=" + deliveryTime
                + ", imagePath=" + imagePath + "]";
    }

}
