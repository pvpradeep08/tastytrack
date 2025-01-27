package com.app.controller;

import java.util.List;

import com.app.dao.OrderDao;
import com.app.entity.Order;

public class OrderController {

    public static boolean addOrder(Order orders) {
        return OrderDao.addOrder(orders);
    }

    public static boolean updateOrder(Order orders) {
        return OrderDao.updateOrder(orders);
    }

    public static boolean deleteOrder(int id) {
        return OrderDao.deleteOrder(id);
    }

    public static boolean deleteAllOrders() {
        return OrderDao.deleteAllOrders();
    }

    public static List<Order> getAllOrders(int id) {
        return OrderDao.getAllOrders(id);
    }

    public static Order getOrder(int id) {
        return OrderDao.getOrder(id);
    }

    public static List<Order> getAllOrdersById(int id) {
        return OrderDao.getAllOrdersById(id);
    }

    public static List<Order> getAllOrdersByStatus(String status) {
        return OrderDao.getAllOrdersByStatus(status);
    }

    public static List<Order> getRecentOrdersByUserId(int id) {
        return OrderDao.getRecentOrdersByUserId(id);
    }

}
