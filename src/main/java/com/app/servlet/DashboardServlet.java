package com.app.servlet;

import java.io.IOException;

import com.app.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("username") != null) {
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("user", user);
            req.getRequestDispatcher("/pages/user.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login.html");
        }
    }
}
