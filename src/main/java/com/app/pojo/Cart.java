package com.app.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public void addCart(CartItem cartItem) {
        int menuId = cartItem.getId();
        if (cart.containsKey(menuId)) {
            CartItem exstingCartItem = cart.get(menuId);
            exstingCartItem.setQuantity(exstingCartItem.getQuantity() + cartItem.getQuantity());
        } else {
            cart.put(menuId, cartItem);
        }
    }

    public void updateCart(int menuId, int quantity) {
        if (cart.containsKey(menuId)) {
            if (quantity <= 0) {
                cart.remove(menuId);
            } else {
                CartItem cartItem = cart.get(menuId);
                cartItem.setQuantity(quantity);
            }
        }
    }

    public void removeCart(int menuId) {
        cart.remove(menuId);
    }

    public Map<Integer, CartItem> getCart() {
        return cart;
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : cart.values()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
