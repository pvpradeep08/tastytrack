package com.app.servlet;

import java.io.IOException;
import java.util.List;

import com.app.dao.OrderDao;
import com.app.entity.Order;
import com.app.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("username") != null) {

            User user = (User) req.getSession().getAttribute("user");
            int userId = user.getUserId();

            List<Order> orders = OrderDao.getAllOrdersById(userId);

            req.getSession().setAttribute("orders", orders);
            req.getRequestDispatcher("/pages/orders.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login.html");
        }

    }
}
