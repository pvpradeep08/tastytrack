package com.app.servlet;

import java.io.IOException;
import java.util.List;

import com.app.controller.MenuController;
import com.app.entity.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));

        List<Menu> menus = MenuController.getAllMenusById(restaurantId);
        req.setAttribute("menus", menus);
        req.getRequestDispatcher("/pages/menu.jsp").forward(req, resp);
    }

}
