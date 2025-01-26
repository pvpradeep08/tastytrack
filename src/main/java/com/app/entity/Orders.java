package com.app.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "total_amount")
    private float totalAmount;

    @Column(name = "total_amount_with_tax")
    private float totalAmountWithTax;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_method")
    private String paymentMethod;

    public Orders() {

    }

    public Orders(int userId, int restaurantId, float totalAmount, float totalAmountWithTax,
            String status, String paymentMethod) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderDate = new Timestamp(System.currentTimeMillis());
        this.totalAmount = totalAmount;
        this.totalAmountWithTax = totalAmountWithTax;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Orders(int orderId, int userId, int restaurantId, float totalAmount,
            float totalAmountWithTax, String status, String paymentMethod) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderDate = new Timestamp(System.currentTimeMillis());
        this.totalAmount = totalAmount;
        this.totalAmountWithTax = totalAmountWithTax;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalAmountWithTax() {
        return totalAmountWithTax;
    }

    public void setTotalAmountWithTax(float totalAmountWithTax) {
        this.totalAmountWithTax = totalAmountWithTax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", userId=" + userId + ", restaurantId=" + restaurantId + ", orderDate="
                + orderDate + ", totalAmount=" + totalAmount + ", totalAmountWithTax=" + totalAmountWithTax
                + ", status=" + status + ", paymentMethod=" + paymentMethod + "]";
    }

}
