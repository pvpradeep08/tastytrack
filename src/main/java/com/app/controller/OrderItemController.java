package com.app.controller;

import java.util.List;

import com.app.dao.OrderItemDao;
import com.app.entity.OrderItem;

public class OrderItemController {

    public static boolean addOrderItem(OrderItem orderItem) {
        return OrderItemDao.addOrderItem(orderItem);
    }

    public static boolean updateOrderItem(OrderItem orderItem) {
        return OrderItemDao.updateOrderItem(orderItem);
    }

    public static boolean deleteOrderItem(int id) {
        return OrderItemDao.deleteOrderItem(id);
    }

    public static boolean deleteAllOrderItems() {
        return OrderItemDao.deleteAllOrderItems();
    }

    public static OrderItem getOrderItem(int id) {
        return OrderItemDao.getOrderItem(id);
    }

    public static List<OrderItem> getAllOrderItemsByOrderId(int id) {
        return OrderItemDao.getAllOrderItemsByOrderId(id);
    }

    public static List<OrderItem> getAllOrderItems() {
        return OrderItemDao.getAllOrderItems();
    }


}
