<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.app.pojo.CartItem" %>
<%@ page import="com.app.entity.User" %>
<%@ page import="com.app.entity.Restaurant" %>
<%@ page import="com.app.pojo.Cart" %>
<%@ page import="com.app.controller.RestaurantController" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../images/logo/fevicon.svg" type="image/x-icon">
    <title>Checkout | Tasty Track</title>
    <link rel="stylesheet" href="../css/checkout.css">
    <link rel="stylesheet" href="../css/navbar.css">
    <link rel="stylesheet" href="../css/footer.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet" />
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</head>

<body>
    <% 
        User user = null;
        Cart cart = null;
        Integer restaurantId = null;
        Restaurant restaurant = null;
        try {
            user = (User) session.getAttribute("user");
            cart = (Cart) session.getAttribute("cart");
            restaurantId = (Integer) session.getAttribute("currentRestaurantId");
            restaurant = RestaurantController.getRestaurant(restaurantId);
        } catch (Exception e) {
            response.sendRedirect("../login.html");
        }
    %>

    <% 
        if (user == null || cart == null || cart.getCart().isEmpty()) {
            return;
        }
    %>

    <nav class="navbar">
        <div class="navbar-container">
            <a href="#" class="logo">
                <img src="../images/logo/logo.svg" alt="" />
            </a>
            <ul class="nav-links">
                <li>
                    <a href="../home"><ion-icon name="home"></ion-icon><span class="nav-text">Home</span></a>
                </li>
                <li>
                    <a href="../restaurants"><ion-icon name="restaurant"></ion-icon><span class="nav-text">Restaurants</span></a>
                </li>
                <li>
                    <a href="../pages/cart.jsp"><ion-icon name="cart" class="cart-icon"></ion-icon><span class="nav-text">Cart</span></a>
                </li>
                <li>
                    <a href="../orderHistory"><ion-icon name="paper-plane"></ion-icon><span class="nav-text">Orders</span></a>
                </li>
                <li>
                    <a href="../dashboard">
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

    <div class="min-h-screen bg-gray-50">
        <main class="max-w-7xl mx-auto px-4 py-8">
            <div class="grid">
                <!-- Left Column - Order Details -->
                <div class="order-details">
                    <div class="bg-white rounded-lg shadow p-6">
                        <h2 class="text-xl font-semibold mb-6">Order Summary</h2>

                        <div class="order-items">
                            <% for (CartItem item : cart.getCart().values()) { %>
                                <div class="order-item">
                                    <div class="item-details">
                                        <img src="../<%= item.getImagePath() %>" alt="<%= item.getName() %>" class="item-image">
                                        <div class="item-info">
                                            <h4><%= item.getName() %></h4>
                                            <p><%= restaurant.getName() %></p>
                                            <p>₹ <%= item.getPrice() %></p>
                                        </div>
                                    </div>
                                    <div class="item-actions">
                                        <div class="item-quantity">
                                            <p>Quantity: <%= item.getQuantity() %></p>
                                        </div>
                                        <div class="item-quantity">
                                            <p>Total: ₹ <%= item.getPrice() * item.getQuantity() %></p>
                                        </div>
                                    </div>
                                </div>
                            <% } %>
                        </div>

                        <div class="subtotal-section">
                            <div class="subtotal-row">
                                <span>Subtotal</span>
                                <span id="subtotal">₹ <%= cart.getTotal() %><span> </span>
                            </div>
                            <div class="subtotal-row">
                                <span>Delivery Fee</span>
                                <span id="deliveryFee">₹</span>
                            </div>
                            <div class="subtotal-row">
                                <span>Tax</span>
                                <span id="tax">₹</span>
                            </div>
                            <div class="total-row">
                                <span class="font-semibold">Total</span>
                                <span id="total" class="font-semibold">₹</span>
                            </div>
                            <div class="delivery-time-row">
                                <span>Estimated Delivery Time</span>
                                <span id="estimatedTime"><%= restaurant.getDeliveryTime() %> - <%= restaurant.getDeliveryTime() + 10 %> min</span>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Right Column - Payment Details -->
                <div class="payment-details">
                    <div class="bg-white rounded-lg shadow p-6">
                        <h2 class="text-xl font-semibold mb-6">Payment Details</h2>

                        <!-- Delivery Address -->
                        <div class="section">
                            <h3 class="section-title">Delivery Address</h3>
                            <div class="address-input">
                                <textarea id="deliveryAddress" placeholder="Enter your delivery address" rows="3" class="address-textarea">
                                    <%= user.getAddress() %>
                                </textarea>
                            </div>
                        </div>

                        <!-- Payment Method -->
                        <div class="section">
                            <h3 class="section-title">Payment Method</h3>
                            <div class="payment-methods">
                                <div class="payment-methods">
                                    <div class="payment-option">
                                        <input type="radio" name="payment" id="card" value="card">
                                        <label for="card">
                                            <span class="icon"><ion-icon name="card-outline"></ion-icon></span>
                                            <div>
                                                <p class="method-name">Card</p>
                                                <p class="method-description">Credit/Debit Card</p>
                                            </div>
                                        </label>
                                    </div>

                                    <div class="payment-option">
                                        <input type="radio" name="payment" id="upi" value="upi">
                                        <label for="upi">
                                            <span class="icon"><ion-icon name="phone-portrait-outline"></ion-icon></span>
                                            <div>
                                                <p class="method-name">UPI</p>
                                                <p class="method-description">Pay using UPI</p>
                                            </div>
                                        </label>
                                    </div>

                                    <div class="payment-option">
                                        <input type="radio" name="payment" id="netbanking" value="netbanking">
                                        <label for="netbanking">
                                            <span class="icon"><ion-icon name="globe-outline"></ion-icon></span>
                                            <div>
                                                <p class="method-name">Net Banking</p>
                                                <p class="method-description">All Indian banks</p>
                                            </div>
                                        </label>
                                    </div>

                                    <div class="payment-option">
                                        <input type="radio" name="payment" id="cod" value="cod">
                                        <label for="cod">
                                            <span class="icon"><ion-icon name="cash-outline"></ion-icon></span>
                                            <div>
                                                <p class="method-name">Cash on Delivery</p>
                                                <p class="method-description">Pay when you receive</p>
                                            </div>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Place Order Button -->
                        <a id="placeOrder" class="button-primary place-order-btn" onclick="placeOrder()">
                            <span>Place Order</span> <ion-icon name="cube-outline" class="place-order-icon"></ion-icon>
                        </a>
                       
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div class="overlay" id="overlay">
        <div class="modal">
            <!-- Inline SVG for scooty -->
            <img width="94" height="94" id="scooty" class="scooty" src="https://img.icons8.com/3d-fluency/94/delivery-scooter.png" alt="delivery-scooter"/>
        </div>
    </div>
    <script src="../js/navbar.js"></script>
    <script src="../js/checkout.js"></script>
</body>

</html>
