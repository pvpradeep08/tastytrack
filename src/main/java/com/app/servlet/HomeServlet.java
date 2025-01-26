package com.app.servlet;

import java.io.IOException;
import java.util.List;

import com.app.controller.MenuController;
import com.app.controller.RestaurantController;
import com.app.entity.Menu;
import com.app.entity.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ( req.getSession().getAttribute("username") != null) {
            List<Restaurant> restaurants = RestaurantController.getAllPopularRestaurants();
            List<Menu> menus = MenuController.getAllPopularMenus();
            req.setAttribute("restaurants", restaurants);
            req.setAttribute("menus", menus);
            req.getRequestDispatcher("pages/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login.html");
        }
    }
}