package com.app.servlet;

import java.io.IOException;

import com.app.controller.MenuController;
import com.app.entity.Menu;
import com.app.entity.User;
import com.app.pojo.Cart;
import com.app.pojo.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            HttpSession session = req.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
            Integer currentRestaurantId = (Integer) session.getAttribute("currentRestaurantId");

            if (cart == null || currentRestaurantId != newRestaurantId) {
                cart = new Cart();
                session.setAttribute("cart", cart);
                session.setAttribute("currentRestaurantId", newRestaurantId);
            }

            String action = req.getParameter("action");

            if (action.equals("add")) {
                addItemToCart(req, cart);
            } else if (action.equals("update")) {
                updateCartItem(req, cart);
            } else if (action.equals("remove")) {
                removeCartItem(req, cart);
            } else {
                resp.sendRedirect("pages/cart.jsp");
            }
            resp.sendRedirect("pages/cart.jsp");
        } else {
            resp.sendRedirect("login.html");
        }

    }

    private void addItemToCart(HttpServletRequest req, Cart cart) {
        int id = Integer.parseInt(req.getParameter("menuId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Menu menu = MenuController.getMenu(id);

        if (menu != null) {
            CartItem item = new CartItem(id, menu.getMenuName(), menu.getDescription(), menu.getImagePath(),
                    menu.getPrice(), quantity);
            cart.addCart(item);
        }
    }

    private void updateCartItem(HttpServletRequest req, Cart cart) {
        int menuId = Integer.parseInt(req.getParameter("menuId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        cart.updateCart(menuId, quantity);
    }

    private void removeCartItem(HttpServletRequest req, Cart cart) {
        int menuId = Integer.parseInt(req.getParameter("menuId"));
        cart.removeCart(menuId);
    }

}
