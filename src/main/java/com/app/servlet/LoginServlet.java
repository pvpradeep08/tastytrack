package com.app.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.app.controller.OrderController;
import com.app.controller.UserController;
import com.app.dao.OrderDao;
import com.app.entity.Order;
import com.app.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = UserController.validateUser(username, password);

        if (user != null) {
            Timestamp lastLoginDate = new Timestamp(System.currentTimeMillis());
            HttpSession session = req.getSession(true);
            session.setAttribute("lastLoginDate", user.getLastLoginDate());

            user.setLastLoginDate(lastLoginDate);
            UserController.updateLastLogin(user);
            session.setAttribute("username", user.getName());
            session.setAttribute("user", user);

            int userId = user.getUserId();

            List<Order> orders = OrderDao.getAllOrdersById(userId);
            int ordersCount = orders.size();
            session.setAttribute("ordersCount", ordersCount);

            List<Order> recentOrders = OrderController.getRecentOrdersByUserId(userId);
            session.setAttribute("recentOrders", recentOrders);

            req.getRequestDispatcher("home").forward(req, resp);
        } else {
            req.setAttribute("message", "User Not Found By This Username or Email");
            req.setAttribute("redirect", "login");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
