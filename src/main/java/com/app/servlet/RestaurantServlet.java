package com.app.servlet;

import java.io.IOException;
import java.util.List;

import com.app.controller.RestaurantController;
import com.app.entity.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurants")
@SuppressWarnings("CallToPrintStackTrace")
public class RestaurantServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ( req.getSession().getAttribute("username") != null) {

            List<Restaurant> restaurants = RestaurantController.getAllRestaurants();
            req.setAttribute("restaurants", restaurants);
            req.getRequestDispatcher("/pages/restaurant.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login.html");
        }
    }
}
