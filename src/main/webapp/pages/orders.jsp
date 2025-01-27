<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.app.controller.MenuController" %>
<%@ page import="com.app.controller.RestaurantController" %>
<%@ page import="com.app.controller.OrderItemController" %>
<%@ page import="com.app.entity.Order" %>
<%@ page import="com.app.entity.OrderItem" %>
<%@ page import="com.app.entity.Restaurant" %>
<%@ page import="com.app.entity.Menu" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History | Tasty Track</title>
    <link rel="stylesheet" href="css/navbar.css" />
    <link rel="stylesheet" href="css/orders.css">
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>

<body>
    <div class="min-h-screen bg-gray-50">
        <!-- Navbar -->
        <nav class="navbar">
            <div class="navbar-container">
                <a href="#" class="logo">
                    <img src="images/logo/logo.svg" alt="" />
                </a>
                <ul class="nav-links">
                    <li><a href="home"><ion-icon name="home"></ion-icon><span class="nav-text">Home</span></a></li>
                    <li><a href="restaurants"><ion-icon name="restaurant"></ion-icon><span class="nav-text">Restaurants</span></a></li>
                    <li><a href="orders"><ion-icon name="paper-plane"></ion-icon><span class="nav-text">Orders</span></a></li>
                    <li><a href="pages/cart.jsp"><ion-icon name="cart" class="cart-icon"></ion-icon><span class="nav-text">Cart</span></a></li>
                    <li><a href="dashboard"><ion-icon name="person-circle" class="profile-icon"></ion-icon><span class="nav-text">
                        <%= (String) session.getAttribute("username") != null ? session.getAttribute("username").toString().split(" ")[0] : "Login" %>
                    </span></a></li>
                </ul>
                <button class="mobile-menu-btn">
                    <ion-icon name="apps-outline"></ion-icon>
                </button>
            </div>
        </nav>

        <!-- Main Content -->
        <main class="max-w-7xl mx-auto px-4 py-8">
            <%
                List<Order> orders = (List<Order>) session.getAttribute("orders");
                if (orders != null && orders.size() > 0) {
            %>
            <!-- Filters and Search -->
            <div class="filters-bar">
                <div class="search-box">
                    <span class="icon"><ion-icon name="search-outline"></ion-icon></span>
                    <input type="text" id="searchOrders" placeholder="Search orders..." onkeyup="filterOrders()">
                </div>

                <div class="filter-options">
                    <select id="statusFilter" class="filter-select" onchange="filterOrders()">
                        <option value="all">All Status</option>
                        <option value="confirmed">Confirmed</option>
                        <option value="delivered">Delivered</option>
                        <option value="processing">Processing</option>
                        <option value="cancelled">Cancelled</option>
                    </select>

                    <select id="sortOrder" class="filter-select" onchange="filterOrders()">
                        <option value="newest">Newest First</option>
                        <option value="oldest">Oldest First</option>
                        <option value="highest">Highest Amount</option>
                        <option value="lowest">Lowest Amount</option>
                    </select>
                </div>
            </div>
            <% } %>

            <!-- Orders List -->
            <div id="ordersContainer" class="orders-container">
                <% if (orders == null || orders.isEmpty()) { %>
                    <div id="noOrders" class="no-orders">
                        <span class="icon">📦</span>
                        <h3>No Orders Found</h3>
                        <p>Try adjusting your filters or search terms</p>
                    </div>
                <% } else {
                    for (Order order : orders) { %>
                    <div class="order-card" data-order-id="<%= order.getOrderId() %>" data-order-status="<%= order.getStatus() %>" data-order-date="<%= order.getOrderDate() %>" data-order-total="<%= order.getTotalAmount() %>">
                        <!-- Order Header -->
                        <div class="order-header">
                            <div>
                                <% int restaurantId = Integer.parseInt(String.valueOf(order.getOrderId())); %>
                                <div class="order-id">ORDERID#<%= order.getOrderId() %></div>
                                <div class="order-date"><%= order.getOrderDate() %></div>
                            </div>
                            <div>
                                <span class="payment-method payment-<%= order.getPaymentMethod() %>"> <%= order.getPaymentMethod() %></span>
                                <span class="order-status status-<%= order.getStatus() %>"><%= order.getStatus() %></span>
                            </div>
                        </div>

                        <!-- Order Items -->
                        <div class="order-items">
                            <% for (OrderItem orderItem : OrderItemController.getAllOrderItemsByOrderId(order.getOrderId())) { %>
                                <div class="order-item">
                                    <% Menu menu = MenuController.getMenu(orderItem.getMenuId()); %>
                                    <% Restaurant restaurant = RestaurantController.getRestaurant(menu.getRestaurantId()); %>
                                    <img src="<%= menu.getImagePath() %>" alt="<%= menu.getMenuName() %>" class="item-image">
                                    <div class="item-details">
                                        <div class="item-name"><%= menu.getMenuName() %></div>
                                        <div class="item-restaurant"><%= restaurant.getName() %></div>
                                    </div>
                                    <div class="item-price">
                                        <%= orderItem.getQuantity() %> x ₹ <%= menu.getPrice() %>
                                    </div>
                                </div>
                            <% } %>
                        </div>

                        <!-- Order Total -->
                        <div class="order-total">
                            <span class="total-label">Total Amount</span>
                            <span class="total-amount">₹ <%= order.getTotalAmount() %></span>
                        </div>
                        <div class="order-total">
                            <span class="total-label">Total Amount With Tax</span>
                            <span class="total-amount total-amount-with-tax">₹ <%= order.getTotalAmountWithTax() %></span>
                        </div>
                    </div>
                <% } } %>
            </div>
        </main>
    </div>

    <!-- JavaScript Files -->
    <script src="js/navbar.js"></script>
    <script src="js/orders.js"></script>
 
</body>

</html>
