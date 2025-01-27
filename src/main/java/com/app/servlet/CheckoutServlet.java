package com.app.servlet;

import java.io.IOException;
import java.util.List;

import com.app.controller.OrderController;
import com.app.controller.OrderItemController;
import com.app.dao.OrderDao;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.entity.User;
import com.app.pojo.Cart;
import com.app.pojo.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.html");
            return;
        }

        int userId = user.getUserId();
        int restaurantId = Integer.parseInt(req.getSession().getAttribute("currentRestaurantId") + "");
        float totalPrice = Float.parseFloat(req.getParameter("totalPrice"));
        float totalPriceWithTax = Float.parseFloat(req.getParameter("totalPriceWithTax"));
        String status = req.getParameter("status");
        String paymentMethod = req.getParameter("paymentMethod");

        Order order = new Order(userId, restaurantId, totalPrice, totalPriceWithTax, status, paymentMethod);

        if (OrderController.addOrder(order)) {
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            for (CartItem cartItem : cart.getCart().values()) {
                OrderItem orderItem = new OrderItem(order.getOrderId(), cartItem.getId(), cartItem.getQuantity(),
                        cartItem.getPrice());
                if (!OrderItemController.addOrderItem(orderItem)) {
                    req.setAttribute("message", "Something went wrong during checkout. Please try again.");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    return;
                }
            }
            req.getSession().setAttribute("cart", new Cart());
            List<Order> orders = OrderDao.getAllOrdersById(userId);
            List<Order> recentOrders = OrderController.getRecentOrdersByUserId(userId);
            req.getSession().setAttribute("recentOrders", recentOrders);
            req.getSession().setAttribute("ordersCount", orders.size());
            req.getRequestDispatcher("home").forward(req, resp);
        } else {
            req.setAttribute("message", "Something went wrong during checkout. Please try again.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
