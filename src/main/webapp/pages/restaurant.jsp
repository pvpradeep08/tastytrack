<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List, com.tastytrack.entity.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Restaurants Near You</title>
		<link rel="stylesheet" href="css/restaurant.css" />
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
		<nav class="navbar">
			<div class="navbar-container">
				<a href="#" class="logo"
					><img src="images/logo/logo.svg" alt=""
				/></a>
				<ul class="nav-links">
					<li>
						<a href="#"
							><ion-icon name="home-outline"></ion-icon
							><span class="nav-text">Home</span></a
						>
					</li>
					<li>
						<a href="#"
							><ion-icon name="restaurant-outline"></ion-icon
							><span class="nav-text">Restaurants</span></a
						>
					</li>
					<li>
						<a href="#"
							><ion-icon name="cart-outline"></ion-icon
							><span class="nav-text">Cart</span></a
						>
					</li>
					<li>
						<a href="#"
							><ion-icon name="person-outline"></ion-icon
							><span class="nav-text">Profile</span></a
						>
					</li>
					<li>
						<a href="#"
							><ion-icon name="pricetag-outline"></ion-icon
							><span class="nav-text">Offers</span></a
						>
					</li>
				</ul>
				<button class="mobile-menu-btn"><ion-icon name="apps-outline"></ion-icon></button>
			</div>
		</nav>
		<header>
			<h1>Restaurants Near You</h1>
		</header>
		<main class="restaurants-container">

			<% List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants"); %>
			<% for (Restaurant r : restaurants) { %>
			<a href="#">
				<div class="restaurant-card">
					<div class="card-image">
						<img
							src="<%= r.getImagePath()%>"
							alt="<%= r.getName() %>"
						/>
						<div class="overlay">
							<span class="cuisine"><%= r.getCuisineType() %></span>
						</div>
						<div class="status-tag <%= r.getIsActive() ? "active" : "inactive" %>"
							><span><%= r.getIsActive() ? "Open" : "Closed" %></span>
							<ion-icon name="<%= r.getIsActive() ? "restaurant" : "close" %>"></ion-icon>
						</div>
					</div>
					<div class="card-content">
						<h2>
							<ion-icon name="restaurant"></ion-icon>
							<span><%= r.getName() %></span>
						</h2>
						<p class="address">
							<ion-icon name="location"></ion-icon>
							<span><%= r.getAddress() %></span>
						</p>
						<div class="time-rating-container">
							<p class="delivery-time">
								<ion-icon name="time"></ion-icon> <span><%= r.getEta() %> - <%= r.getEta() + 10 %> min</span>
							</p>
							<div class="rating">
								<p class="rating-value"><%= r.getRating() %></p>
								<ion-icon name="star"></ion-icon>
							</div>
						</div>
					</div></div
			></a>
			<% } %>











		</main>
		<footer class="footer">
			<div class="footer-content">
				<div class="footer-section">
					<a href="#" class="logo"
						><img src="images/logo/logo.svg" alt=""
					/></a>
					<p style="margin-top: 25px;" >
						Delivering your favorite meals right to your doorstep. Fast, fresh,
						and delicious!
					</p>
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
						<a
							href="https://www.linkedin.com/in/vignesh-muthusamy-/"
							aria-label="LinkedIn"
							target="_blank"
							><ion-icon
								class="social-icon-logo"
								name="logo-linkedin"
							></ion-icon
						></a>
						<a
							href="https://github.com/vicky11vicky11"
							aria-label="Github"
							target="_blank"
							><ion-icon class="social-icon-logo" name="logo-github"></ion-icon
						></a>
						<a
							href="https://wa.me/+916369531042"
							aria-label="Whatsapp"
							target="_blank"
							><ion-icon
								class="social-icon-logo"
								name="logo-whatsapp"
							></ion-icon
						></a>
						<a
							href="https://www.instagram.com/_.mr_vignesh._/"
							aria-label="Whatsapp"
							target="_blank"
							><ion-icon
								class="social-icon-logo"
								name="logo-instagram"
							></ion-icon
						></a>
						<a href="mailto:vickyvignesh430329@gmail.com" target="_blank"
							><ion-icon name="mail-unread-outline"></ion-icon
						></a>
						<a
							href="https://vignesh-website.netlify.app/"
							aria-label="Portfolio"
							target="_blank"
							><ion-icon
								class="social-icon-logo"
								name="person-circle"
							></ion-icon
						></a>
					</div>
				</div>
			</div>
			<div class="footer-bottom">
				<p>&copy; 2025 TastyTrack. All rights reserved.</p>
			</div>
		</footer>
		<script src="js/navbar.js"></script>
	</body>
</html>
