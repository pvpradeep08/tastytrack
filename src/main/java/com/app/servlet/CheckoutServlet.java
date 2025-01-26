package com.app.servlet;

import java.io.IOException;

import com.app.controller.OrdersController;
import com.app.entity.Orders;
import com.app.entity.User;

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
        int userId = user.getUserId();
        int restaurantId = Integer.parseInt(req.getSession().getAttribute("currentRestaurantId") + "");
        float totalPrice = Float.parseFloat(req.getParameter("totalPrice"));
        float totalPriceWithTax = Float.parseFloat(req.getParameter("totalPriceWithTax"));
        String status = req.getParameter("status");
        String paymentMethod = req.getParameter("paymentMethod");

        Orders order = new Orders(userId, restaurantId, totalPrice, totalPriceWithTax, status, paymentMethod);

        if (OrdersController.addOrder(order)) {
            req.getSession().removeAttribute("cart");

            req.getRequestDispatcher("home").forward(req, resp);
        } else {
            req.setAttribute("message", "Something went wrong during checkout. Please try again.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
