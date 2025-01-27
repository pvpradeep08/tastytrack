<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.app.entity.User" %>
<%@ page import="com.app.entity.Order" %>
<%@ page import="com.app.entity.OrderItem" %>
<%@ page import="com.app.controller.OrderItemController" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User Profile - Food Delivery</title>
    <link rel="shortcut icon" href="images/logo/fevicon.svg" type="image/x-icon" />
    <link rel="stylesheet" href="css/user.css" />
    <link rel="stylesheet" href="css/navbar.css" />
    <link rel="stylesheet" href="css/footer.css" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet" />
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>
<body>
    <div class="min-h-screen bg-gray-50">
        <nav class="navbar">
            <div class="navbar-container">
                <a href="#" class="logo">
                    <img src="images/logo/logo.svg" alt="Logo" />
                </a>
                <ul class="nav-links">
                    <li>
                        <a href="home">
                            <ion-icon name="home"></ion-icon>
                            <span class="nav-text">Home</span>
                        </a>
                    </li>
                    <li>
                        <a href="restaurants">
                            <ion-icon name="restaurant"></ion-icon>
                            <span class="nav-text">Restaurants</span>
                        </a>
                    </li>
                    <li>
                        <a href="pages/cart.jsp">
                            <ion-icon name="cart" class="cart-icon"></ion-icon>
                            <span class="nav-text">Cart</span>
                        </a>
                    </li>
                    <li>
                        <a href="orders">
                            <ion-icon name="paper-plane"></ion-icon>
                            <span class="nav-text">Orders</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <ion-icon name="person-circle" class="profile-icon"></ion-icon>
                            <span class="nav-text">
                                <%= (String) session.getAttribute("username") != null ? session.getAttribute("username").toString().split(" ")[0] : "Login" %>
                            </span>
                        </a>
                    </li>
                </ul>
                <button class="mobile-menu-btn">
                    <ion-icon name="apps-outline"></ion-icon>
                </button>
            </div>
        </nav>

        <% User user = (User) request.getAttribute("user"); %>

        <main class="max-w-7xl mx-auto px-4 py-8">
            <div class="grid">
                <!-- Profile Card -->
                <div class="profile-card">
                    <div class="bg-white rounded-lg shadow p-6">
                        <div class="flex-col items-center text-center">
                            <img
                                src="https://images.unsplash.com/profile-1446404465118-3a53b909cc82?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=27a346c2362207494baa7b76f5d606e5"
                                alt="Profile Picture"
                                class="profile-image"
                            />
                            <h2 class="mt-4 text-xl font-semibold text-gray-900">
                                <%= user.getName() %>
                            </h2>
                            <p class="text-gray-500 text-sm" id="member-since">
                                <%= user.getCreatedAt() %>
                            </p>
                            <p class="text-gray-500 text-sm" id="lastLogin">
                                <%= user.getLastLoginDate() %>
                            </p>
                            <p class="text-gray-500 text-sm" id="accountCreated">
                                <%= user.getCreatedAt() %>
                            </p>

                            <div class="stats-container">
                                <div class="stat">
                                    <p class="text-xl font-semibold text-gray-900">
                                        <%= session.getAttribute("ordersCount") != null ? session.getAttribute("ordersCount").toString() : " 0 " %>
                                    </p>
                                    <p class="text-sm text-gray-500">Orders</p>
                                </div>
                            </div>
                        </div>

                        <div class="quick-actions">
                            <a class="action-button" href="orders">
                                <span class="icon">📦</span>
                                My Orders
                            </a>
                            <a class="action-button" href="pages/cart.jsp">
                                <span class="icon">🛒</span>
                                Cart
                            </a>
                            <a href="logout" class="action-button danger">
                                <span class="icon">🚪</span>
                                Sign Out
                            </a>
                        </div>
                    </div>
                </div>

                <!-- Details Section -->
                <div class="details-section">
                    <div class="bg-white rounded-lg shadow">
                        <div class="p-6">
                            <h3 class="text-lg font-semibold text-gray-900 mb-4">
                                Personal Information
                            </h3>
                            <div class="info-grid">
                                <div class="info-item">
                                    <span class="icon">👤</span>
                                    <div>
                                        <p class="text-sm text-gray-500">Full Name</p>
                                        <p class="text-gray-900"><%= user.getName() %></p>
                                    </div>
                                </div>
                                <div class="info-item">
                                    <span class="icon">📧</span>
                                    <div>
                                        <p class="text-sm text-gray-500">Email</p>
                                        <p class="text-gray-900"><%= user.getEmail() %></p>
                                    </div>
                                </div>
                                <div class="info-item">
                                    <span class="icon">📱</span>
                                    <div>
                                        <p class="text-sm text-gray-500">Phone</p>
                                        <p class="text-gray-900"><%= user.getPhone() %></p>
                                    </div>
                                </div>
                                <div class="info-item">
                                    <span class="icon">📍</span>
                                    <div>
                                        <p class="text-sm text-gray-500">Delivery Address</p>
                                        <p class="text-gray-900"><%= user.getAddress() %></p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="border-t border-gray-200 p-6">
                            <h3 class="text-lg font-semibold text-gray-900 mb-4">
                                Recent Orders
                            </h3>
                            <div class="orders-list">
                                <% List<Order> recentOrders = (List<Order>) session.getAttribute("recentOrders"); %>
                                <% if (recentOrders != null && recentOrders.size() > 0) { 
                                    for (Order order : recentOrders) { 
                                        List<OrderItem> orderItems = OrderItemController.getAllOrderItemsByOrderId(order.getOrderId()); %>
                                        <div class="order-item">
                                            <div class="flex items-center space-x-4">
                                                <div class="bg-white p-2 rounded-md">
                                                    <span class="icon">📦</span>
                                                </div>
                                                <div>
                                                    <p class="font-medium text-gray-900">Order #<%= order.getOrderId() %></p>
                                                    <p class="text-sm text-gray-500"><%= orderItems.size() %> items • ₹ <%= order.getTotalAmountWithTax() %></p>
                                                </div>
                                            </div>
                                            <div class="flex items-center">
                                                <span class="icon">🕒</span>
                                                <span class="text-sm text-gray-500 recent-order-date"><%= order.getOrderDate() %></span>
                                            </div>
                                        </div>
                                    <% } } else { %>
                                        <div class="order-item">
                                            <div class="flex items-center space-x-4">
                                                <p>No recent orders</p>
                                            </div>
                                        </div>
                                    <% } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <script src="js/user.js"></script>
    <script src="js/navbar.js"></script>
</body>
</html>
