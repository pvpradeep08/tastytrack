<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.app.entity.Menu" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    	<link rel="shortcut icon" href="images/logo/fevicon.svg" type="image/x-icon">

		<title>Restaurant Menu | Tasty Track</title>
		<link rel="stylesheet" href="css/menu.css" />
		<link rel="stylesheet" href="css/navbar.css">
		<link rel="stylesheet" href="css/footer.css">
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
                        <a href="orders"
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
                        <a href="dashboard"
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
		<header>
			<h1><span><%= request.getAttribute("restaurantName") %>'s Menu Items</span> <ion-icon name="restaurant"></ion-icon></h1>
		</header>
		<main class="menu-container">


		<% List<Menu> menus = (List<Menu>) request.getAttribute("menus"); %>
		<% for( Menu menu : menus ) { %>
			<div class="menu-item <%=menu.isAvailable()?"":"unavailable"%> ">
				<img
					src="<%= menu.getImagePath() %>"
					alt="<%= menu.getMenuName() %>"
				/>
				<h2><%= menu.getMenuName() %></h2>
				<p class="description">
					<%= menu.getDescription() %>
				</p>
				<div class="details">
					<span class="rating">★ <%= menu.getRating() %></span>
					<span class="price" data-base-price="<%= menu.getPrice() %>">₹ <%= menu.getPrice() %></span>
				</div>
				<form class="cart-controls" action="cart" >
					<input type="hidden" name="menuId" value="<%= menu.getMenuId() %>" >
					<input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId()%>" >
					<input type="hidden" name="quantity" value="1" >
					<input type="hidden" name="action" value="add" >
					<button type="submit" class="add-to-cart">Add to Cart</button>
				</form>
			</div>

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
