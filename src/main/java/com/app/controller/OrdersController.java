package com.app.controller;

import java.util.List;

import com.app.dao.OrdersDao;
import com.app.entity.Orders;

public class OrdersController {

    public static boolean addOrder(Orders orders) {
        return OrdersDao.addOrder(orders);
    }

    public static boolean updateOrder(Orders orders) {
        return OrdersDao.updateOrder(orders);
    }

    public static boolean deleteOrder(int id) {
        return OrdersDao.deleteOrder(id);
    }

    public static boolean deleteAllOrders() {
        return OrdersDao.deleteAllOrders();
    }

    public static List<Orders> getAllOrders() {
        return OrdersDao.getAllOrders();
    }

    public static Orders getOrder(int id) {
        return OrdersDao.getOrder(id);
    }

    public static List<Orders> getAllOrdersById(int id) {
        return OrdersDao.getAllOrdersById(id);
    }

    public static List<Orders> getAllOrdersByStatus(String status) {
        return OrdersDao.getAllOrdersByStatus(status);
    }

    public static List<Orders> getRecentOrders() {
        return OrdersDao.getRecentOrders();
    }

}
