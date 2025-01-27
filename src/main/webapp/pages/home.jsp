<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.app.entity.Menu" %>
<%@ page import="com.app.entity.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Home | Tasty Track</title>
		<link rel="shortcut icon" href="images/logo/fevicon.svg" type="image/x-icon" />
		<link rel="stylesheet" href="css/home.css" />
		<link rel="stylesheet" href="css/navbar.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet" />
		<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
		<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
	</head>
	<body>
        <nav class="navbar">
            <div class="navbar-container">
                <a href="#" class="logo"><img src="images/logo/logo.svg" alt="" /></a>
                <ul class="nav-links">
                    <li><a href="home"><ion-icon name="home"></ion-icon><span class="nav-text">Home</span></a></li>
                    <li><a href="restaurants"><ion-icon name="restaurant"></ion-icon><span class="nav-text">Restaurants</span></a></li>
                    <li><a href="pages/cart.jsp"><ion-icon name="cart" class="cart-icon"></ion-icon><span class="nav-text">Cart</span></a></li>
                    <li><a href="orders"><ion-icon name="paper-plane"></ion-icon><span class="nav-text">Orders</span></a></li>
                    <li><a href="dashboard"><ion-icon name="person-circle" class="profile-icon"></ion-icon><span class="nav-text"> 
                        <%= (String) session.getAttribute("username") != null ? session.getAttribute("username").toString().split(" ")[0] : "Login" %>
                    </span></a></li>
                </ul>
                <button class="mobile-menu-btn"><ion-icon name="apps-outline"></ion-icon></button>
            </div>
        </nav>

		<header>
			<h1><span>Welcome to Tasty Track</span><ion-icon name="map-outline"></ion-icon></h1>
		</header>

		<% List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants"); %>
		<% List<Menu> menus = (List<Menu>) request.getAttribute("menus"); %>

		<div class="container">
			<section id="restaurants">
				<div class="slider-head">
					<h2>Popular Restaurants</h2>
					<a href="restaurants" class="view-all">View All <ion-icon name="arrow-forward"></ion-icon></a>
				</div>
				<div class="slider-container">
					<div class="slider" id="restaurant-slider">
						<% for(Restaurant r : restaurants){ %>
							<a href="menu?restaurantId=<%= r.getRestaurantId() %>" class="slider-link">
								<div class="slide">
									<img src="<%= r.getImagePath() %>" alt="<%= r.getName() %>" />
									<h3><%= r.getName() %></h3>
									<p><%= r.getAddress() %></p>
									<div class="details">
										<div class="tag"><%= r.getCuisineType() %></div>
										<div class="rating"><%= r.getRating() %> <ion-icon name="star"></ion-icon></div>
									</div>
								</div>
							</a>
						<% } %>
					</div>
					<button class="slider-btn prev" onclick="slideRestaurants(-1)">
						<ion-icon name="chevron-back-outline"></ion-icon>
					</button>
					<button class="slider-btn next" onclick="slideRestaurants(1)">
						<ion-icon name="chevron-forward-outline"></ion-icon>
					</button>
				</div>
			</section>

			<section id="dishes">
				<div class="slider-head">
					<h2>Popular Dishes</h2>
					<a href="restaurants" class="view-all">View All <ion-icon name="arrow-forward"></ion-icon></a>
				</div>
				<div class="slider-container">
					<div class="slider" id="dish-slider">
						<% for(Menu m : menus){ %>
							<a class="slider-link" href="menu?restaurantId=<%= m.getRestaurantId() %>">
								<div class="slide">
									<img src="<%= m.getImagePath() %>" alt="<%= m.getMenuName() %>" />
									<h3><%= m.getMenuName() %></h3>
									<p><%= m.getDescription() %></p>
									<div class="details">
										<div class="price">₹ <%= m.getPrice() %></div>
										<div class="rating"><%= m.getRating() %> <ion-icon name="star"></ion-icon></div>
									</div>
								</div>
							</a>
						<% } %>
					</div>
					<button class="slider-btn prev" onclick="slideDishes(-1)">
						<ion-icon name="chevron-back-outline"></ion-icon>
					</button>
					<button class="slider-btn next" onclick="slideDishes(1)">
						<ion-icon name="chevron-forward-outline"></ion-icon>
					</button>
				</div>
			</section>
		</div>

		<footer class="footer">
			<div class="footer-content">
				<div class="footer-section">
					<a href="#" class="logo"><img src="images/logo/logo.svg" alt="" /></a>
					<p style="margin-top: 25px">Delivering your favorite meals right to your doorstep. Fast, fresh, and delicious!</p>
				</div>
				<div class="footer-section">
					<h3>Quick Links</h3>
					<ul>
						<li><a href="#">Restaurants</a></li>
						<li><a href="#">Cuisines</a></li>
						<li><a href="#">Offers</a></li>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
				</div>
				<div class="footer-section">
					<h3>Customer Support</h3>
					<ul>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">Terms of Service</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="#">Refund Policy</a></li>
					</ul>
				</div>
				<div class="footer-section">
					<h3>Connect With Us</h3>
					<div class="social-icons">
						<a href="https://www.linkedin.com/in/vignesh-muthusamy-/" aria-label="LinkedIn" target="_blank">
							<ion-icon class="social-icon-logo" name="logo-linkedin"></ion-icon>
						</a>
						<a href="https://github.com/vicky11vicky11" aria-label="Github" target="_blank">
							<ion-icon class="social-icon-logo" name="logo-github"></ion-icon>
						</a>
						<a href="https://wa.me/+916369531042" aria-label="Whatsapp" target="_blank">
							<ion-icon class="social-icon-logo" name="logo-whatsapp"></ion-icon>
						</a>
						<a href="https://www.instagram.com/_.mr_vignesh._/" aria-label="Instagram" target="_blank">
							<ion-icon class="social-icon-logo" name="logo-instagram"></ion-icon>
						</a>
						<a href="mailto:vickyvignesh430329@gmail.com" target="_blank">
							<ion-icon name="mail-unread-outline"></ion-icon>
						</a>
						<a href="https://vignesh-website.netlify.app/" aria-label="Portfolio" target="_blank">
							<ion-icon class="social-icon-logo" name="person-circle"></ion-icon>
						</a>
					</div>
				</div>
			</div>
			<div class="footer-bottom">
				<p>&copy; 2025 TastyTrack. All rights reserved.</p>
			</div>
		</footer>

		<script src="js/navbar.js"></script>
		<script src="js/home.js"></script>
	</body>
</html>
