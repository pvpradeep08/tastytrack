package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "rating")
    private float rating;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "popular")
    private boolean popular;

    public Menu() {
    }

    public Menu(int restaurantId, String menuName, String description, float price, float rating,
            boolean isAvailable, String imagePath, boolean popular) {
        this.restaurantId = restaurantId;
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;
        this.popular = popular;
    }

    public Menu(int menuId, int restaurantId, String menuName, String description, float price, float rating,
            boolean isAvailable, String imagePath, boolean popular) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", menuName=" + menuName + ", description="
                + description + ", price=" + price + ", rating=" + rating + ", isAvailable=" + isAvailable
                + ", imagePath=" + imagePath + ", popular=" + popular + "]";
    }

}
