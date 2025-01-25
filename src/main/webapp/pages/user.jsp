<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="java.util.List" %> --%>
<%@ page import="com.app.entity.User" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>User Profile - Food Delivery</title>
		<link rel="stylesheet" href="css/user.css" />
		<link rel="stylesheet" href="css/navbar.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<link
			href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
			rel="stylesheet"
		/>
		<script
			type="module"
			src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"
		></script>
		<script
			nomodule
			src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"
		></script>
	</head>
	<body>
		<div class="min-h-screen bg-gray-50">
			<nav class="navbar">
            <div class="navbar-container">
                <a href="#" class="logo"
                    ><img src="images/logo/logo.svg" alt=""
                /></a>
                <ul class="nav-links">
                    <li>
                        <a href="home"
                            ><ion-icon name="home"></ion-icon
                            ><span class="nav-text">Home</span></a
                        >
                    </li>
                    <li>
                        <a href="restaurants"
                            ><ion-icon name="restaurant"></ion-icon
                            ><span class="nav-text">Restaurants</span></a
                        >
                    </li>
                    <li>
                        <a href="#"
                            ><ion-icon name="paper-plane"></ion-icon><span class="nav-text">Orders</span></a
                        >
                    </li>
                    <li>
                        <a href="pages/cart.jsp"
                            ><ion-icon name="cart" class="cart-icon"></ion-icon
                            ><span class="nav-text">Cart</span></a
                        >
                    </li>
                    <li>
                        <a href="#"
                            ><ion-icon name="person-circle" class="profile-icon"></ion-icon
                            ><span class="nav-text"> <%= (String) session.getAttribute("username") != null ? session.getAttribute("username").toString().split(" ")[0] : "Login" %> </span></a
                        >
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
					<!-- Left Column - Profile Card -->
					<div class="profile-card">
						<div class="bg-white rounded-lg shadow p-6">
							<div class="flex-col items-center text-center">
								<img
									src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&q=80&w=200&h=200"
									alt="Sarah Johnson"
									class="profile-image"
								/>
								<h2 class="mt-4 text-xl font-semibold text-gray-900">
									<%= user.getName() %>
								</h2>
								<p class="text-gray-500 text-sm" id="member-since" ><%= user.getCreatedAt()
								 %></p>
								<p class="text-gray-500 text-sm" id="lastLogin">
									<%= user.getLastLoginDate() %>
								</p>
								<p class="text-gray-500 text-sm" id="accountCreated">
									<%= user.getCreatedAt() %>
								</p>

								<!-- Stats -->
								<div class="stats-container">
									<div class="stat">
										<p class="text-xl font-semibold text-gray-900">43</p>
										<p class="text-sm text-gray-500">Orders</p>
									</div>
								</div>
							</div>

							<!-- Quick Actions -->
							<div class="quick-actions">
								<button class="action-button">
									<span class="icon">📦</span>
									My Orders
								</button>
								<button class="action-button">
									<span class="icon">🛒</span>
									Cart
								</button>
								<button class="action-button danger">
									<span class="icon">🚪</span>
									Sign Out
								</button>
							</div>
						</div>
					</div>

					<!-- Right Column - Details -->
					<div class="details-section">
						<div class="bg-white rounded-lg shadow">
							<!-- Personal Information -->
							<div class="p-6">
								<h3 class="text-lg font-semibold text-gray-900 mb-4">
									Personal Information
								</h3>
								<div class="info-grid">
									<div class="info-item">
										<span class="icon">👤</span>
										<div>
											<p class="text-sm text-gray-500">Full Name</p>
											<p class="text-gray-900"><%= user.getName().toString() %></p>
										</div>
									</div>
									<div class="info-item">
										<span class="icon">📧</span>
										<div>
											<p class="text-sm text-gray-500">Email</p>
											<p class="text-gray-900"><%= user.getEmail().toString() %></p>
										</div>
									</div>
									<div class="info-item">
										<span class="icon">📱</span>
										<div>
											<p class="text-sm text-gray-500">Phone</p>
											<p class="text-gray-900"><%= user.getPhone().toString() %></p>
										</div>
									</div>
									<div class="info-item">
										<span class="icon">📍</span>
										<div>
											<p class="text-sm text-gray-500">Delivery Address</p>
											<p class="text-gray-900">
												<%= user.getAddress().toString() %>
											</p>
										</div>
									</div>
								</div>
							</div>

							<!-- Recent Orders -->
							<div class="border-t border-gray-200 p-6">
								<h3 class="text-lg font-semibold text-gray-900 mb-4">
									Recent Orders
								</h3>
								<div class="orders-list"></div>
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

