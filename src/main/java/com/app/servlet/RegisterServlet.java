package com.app.servlet;

import java.io.IOException;

import com.app.controller.UserController;
import com.app.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = (String) req.getParameter("name");
        String username = (String) req.getParameter("username");
        String email = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");
        String phone = (String) req.getParameter("phone");
        String role = (String) req.getParameter("role");
        String address = (String) req.getParameter("address");

        // Check if user already exists
        User existingUserByUsername = UserController.getUserByUsername(username);
        User existingUserByEmail = UserController.getUserByEmail(email);

        System.out.println(existingUserByUsername);
        System.out.println(existingUserByEmail);

        if (existingUserByUsername != null && existingUserByEmail != null) {
            req.setAttribute("message", "Username and Email already in use. Please choose a different one.");
            req.setAttribute("redirect", "signup");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else if (existingUserByUsername != null) {
            req.setAttribute("message", "Username already taken. Please choose a different one.");
            req.setAttribute("redirect", "signup");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else if (existingUserByEmail != null) {
            req.setAttribute("message", "Email already in use. Please choose a different one.");
            req.setAttribute("redirect", "signup");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else {
            User user = new User(name, username, email, password, phone, address, role);
            boolean result = UserController.addUser(user);

            if (result == true) {
                resp.sendRedirect("login.html"); // Redirect to login page
            } else {
                req.setAttribute("message", "Registration failed. Please try again.");
                req.setAttribute("redirect", "register");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }

    }
}
