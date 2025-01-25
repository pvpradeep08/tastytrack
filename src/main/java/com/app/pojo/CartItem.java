package com.app.pojo;

public class CartItem {
    private int id;
    private String name;
    private String description;
    private String imagePath;
    private float price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int id, String name, String description, String imagePath, float price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem [id=" + id + ", name=" + name + ", description=" + description + ", imagePath=" + imagePath
                + ", price=" + price + ", quantity=" + quantity + "]";
    }

}
