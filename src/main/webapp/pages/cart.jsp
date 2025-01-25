<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.app.pojo.CartItem"%>
<%@ page import="com.app.pojo.Cart"%>
<%-- <%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%> --%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link
			rel="shortcut icon"
			href="../images/logo/fevicon.svg"
			type="image/x-icon"
		/>

		<title>Your Cart | Tasty Track</title>
		<link rel="stylesheet" href="../css/cart.css" />
		<link rel="stylesheet" href="../css/navbar.css" />
		<link rel="stylesheet" href="../css/footer.css" />
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
					><img src="../images/logo/logo.svg" alt=""
				/></a>
				<ul class="nav-links">
					<li>
						<a href="../home"
							><ion-icon name="home"></ion-icon
							><span class="nav-text">Home</span></a
						>
					</li>
					<li>
						<a href="../restaurants"
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
					<% String sampleAction = "nothing";  %>
						<a href="../cart?restaurantId=<%= session.getAttribute("currentRestaurantId") != null ? session.getAttribute("currentRestaurantId") : 0 %>&action=<%= sampleAction %>"
							><ion-icon name="cart"></ion-icon
							><span class="nav-text">Cart</span></a
						>
					</li>
					<li>
						<a href="../dashboard"
							><ion-icon name="person-circle"></ion-icon
							><span class="nav-text"><%= (String) session.getAttribute("username") != null ? session.getAttribute("username").toString().split(" ")[0] : "Login" %></span></a
						>
					</li>
				</ul>
				<button class="mobile-menu-btn">
					<ion-icon name="apps-outline"></ion-icon>
				</button>
			</div>
		</nav>
		<div class="cart-container">
			<h1><span>Your Cart</span> <ion-icon name="cart"></ion-icon></h1>

			
			<div id="cart-items">

				<% Cart cart = (Cart) session.getAttribute("cart");
				Integer currentRestaurantId = (Integer) session.getAttribute("currentRestaurantId"); 

					if(cart == null || cart.getCart().isEmpty()) {
						%>
						<div class="empty-cart">
							<ion-icon name="trash-bin"></ion-icon>
							<h2>Your cart is empty</h2>
							<p>Add items to your cart to continue ordering</p>
							<a href="../home"> Start Ordering </a>
						</div>
						<%
						return;
					}
					else {
						for(CartItem item : cart.getCart().values()) { %>
				<div class="cart-item">
					<div class="item-left">
						<div class="item-image-box">
							<img
							src="../<%= item.getImagePath() %>"
							alt="<%= item.getName() %>"
							class="item-image" />
						</div>
						<div class="item-details">
							<div class="item-name"><%= item.getName() %></div>
							<div class="item-description">
								<%= item.getDescription() %>
							</div>
						</div>
					</div>
					<div class="item-right">
						<div class="item-quantity">
							<form action="../cart" method="post">
								<input type="hidden" name="menuId" value="<%= item.getId() %>" />
								<input type="hidden" name="action" value="update" >
								<input type="hidden" name="restaurantId" value="<%= currentRestaurantId %>" >
								<input type="hidden" name="quantity" value="<%= item.getQuantity() -1 %>" >
								<button class="quantity-btn" type="submit" <% if (item.getQuantity() == 1) { %> disabled <% } %> >
									-
								</button>
							</form>
							<span class="quantity" id="quantity-1"><%= item.getQuantity() %></span>
							<form action="../cart" method="post">
								<input type="hidden" name="menuId" value="<%= item.getId() %>" />
								<input type="hidden" name="action" value="update" >
								<input type="hidden" name="restaurantId" value="<%= (Integer) session.getAttribute("currentRestaurantId") %>" >
								<input type="hidden" name="quantity" value="<%= item.getQuantity() +1 %>" >
								<button class="quantity-btn" type="submit" >
									+
								</button>
							</form>
						</div>
						<div class="item-price" id="price-1"> ₹<%= item.getPrice() * item.getQuantity() %></div>
							<form action="../cart" class="item-clear" >
								<input type="hidden" name="restaurantId" value="<%= (Integer) session.getAttribute("currentRestaurantId") %>" >
								<input type="hidden" name="menuId" value="<%= item.getId() %>" />
								<input type="hidden" name="action" value="remove" >
								<button class="remove-item" type="submit" >
									<ion-icon class="remove-item-icon" name="trash"></ion-icon>
								</button>
							</form>
					</div>
				</div>
					<%	}
					}	
				%>


				

				
			</div>
			<% if(cart != null && !cart.getCart().isEmpty()) { %>
			<div class="cart-summary">
				<div class="cart-total">
					Total Price: ₹<span id="cart-total"><%= cart.getTotal() %></span>
				</div>
				<a href="../menu?restaurantId=<%= currentRestaurantId %>" class="add-more">Add More Items to Cart</a>
				<a class="checkout-btn" href="../pages/checkout.html" >
					Proceed to Checkout
				</a>
			</div>
			<% } %>
		</div>

		<footer class="footer">
			<div class="footer-content">
				<div class="footer-section">
					<a href="#" class="logo"
						><img src="../images/logo/logo.svg" alt=""
					/></a>
					<p style="margin-top: 25px">
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
		<!-- <script src="script.js"></script> -->
		<%-- <script src="js/cart.js"></script> --%>
		<script src="../js/navbar.js"></script>
	</body>
</html>
