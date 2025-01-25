package com.app.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.app.controller.UserController;
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
            Timestamp lastLoginDate = new java.sql.Timestamp(System.currentTimeMillis());
            user.setLastLoginDate(lastLoginDate);
            UserController.updateLastLogin(user);
            HttpSession session = req.getSession(true);
            session.setAttribute("username", user.getName());
            session.setAttribute("user", user);
            req.getRequestDispatcher("home").forward(req, resp);
        } else {
            req.getRequestDispatcher("/pages/error.html").forward(req, resp);
        }
    }
}
